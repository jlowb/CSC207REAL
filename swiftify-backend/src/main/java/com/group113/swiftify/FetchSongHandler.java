package com.group113.swiftify;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FetchSongHandler {

    public Map<String, Object> handleRequest(Map<String, Object> input) {
        DynamoDbClient dynamoDb = DependencyFactory.dynamoDbClient();
        S3Presigner s3Presigner = DependencyFactory.s3Presigner();

        // Extract pathParameters from input and then the SongID
        Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
        String songIdStr = pathParameters.get("SongID");
        Integer songId = Integer.valueOf(songIdStr);

        GetItemRequest getItemRequest = GetItemRequest.builder()
                .tableName("MusicMetadata")
                .key(new HashMap<String, AttributeValue>() {{
                    put("SongID", AttributeValue.builder().n(songId.toString()).build());
                }})
                .build();

        GetItemResponse getItemResponse = dynamoDb.getItem(getItemRequest);
        Map<String, AttributeValue> item = getItemResponse.item();

        // Convert DynamoDB attributes to a simple Map<String, Object>
        Map<String, Object> metadata = item.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> convertAttributeValue(entry.getValue())
                ));

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("csc207swiftify")
                .key(metadata.get("s3_key").toString())
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofHours(1))
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(presignRequest);

        String url = presignedGetObjectRequest.url().toString();

        Map<String, Object> response = new HashMap<>();
        response.put("metadata", metadata);
        response.put("presignedUrl", url);

        return response;
    }

    private Object convertAttributeValue(AttributeValue value) {
        if (value.s() != null) {
            return value.s();
        } else if (value.n() != null) {
            return value.n();
        } else if (value.bool() != null) {
            return value.bool();
        } else if (value.m() != null) {
            return value.m().entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            e -> convertAttributeValue(e.getValue())
                    ));
        } else if (value.l() != null) {
            return value.l().stream()
                    .map(this::convertAttributeValue)
                    .collect(Collectors.toList());
        }
        return null;
    }
}