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

public class FetchSongHandler {

    public Map<String, Object> handleRequest(Map<String, Object> input) {
        // Initialize DynamoDB and S3 Presigner clients using DependencyFactory
        DynamoDbClient dynamoDb = DependencyFactory.dynamoDbClient();
        S3Presigner s3Presigner = DependencyFactory.s3Presigner();

        // Fetch SongID from input
        Integer songId = (Integer) input.get("SongID");

        // Prepare DynamoDB GetItem request with the correct AttributeValue
        GetItemRequest getItemRequest = GetItemRequest.builder()
                .tableName("MusicMetadata")
                .key(new HashMap<String, AttributeValue>() {{
                    put("SongID", AttributeValue.builder().n(songId.toString()).build());
                }})
                .build();

        // Fetch item from DynamoDB
        GetItemResponse getItemResponse = dynamoDb.getItem(getItemRequest);
        Map<String, AttributeValue> item = getItemResponse.item();

        // Prepare S3 GetObject request
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("csc207swiftify")
                .key(item.get("s3_key").s())
                .build();

        // Generate a pre-signed URL for the S3 object
        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofHours(1))
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(presignRequest);

        String url = presignedGetObjectRequest.url().toString();

        // Prepare and return the response
        Map<String, Object> response = new HashMap<>();
        response.put("metadata", item);
        response.put("presignedUrl", url);

        return response;
    }
}