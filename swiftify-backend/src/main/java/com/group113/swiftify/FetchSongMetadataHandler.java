package com.group113.swiftify;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class FetchSongMetadataHandler {

    public Map<String, Object> handleRequest(Map<String, Object> input) {
        DynamoDbClient dynamoDb = DependencyFactory.dynamoDbClient();

        // Extract pathParameters from input and then the SongID
        Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
        String songIdStr = pathParameters.get("SongID");
        Integer songId = Integer.valueOf(songIdStr);

        GetItemRequest getItemRequest = GetItemRequest.builder()
                .tableName("MusicMetadata")
                .key(Collections.singletonMap("SongID", AttributeValue.builder().n(songId.toString()).build()))
                .build();

        GetItemResponse getItemResponse = dynamoDb.getItem(getItemRequest);
        Map<String, AttributeValue> item = getItemResponse.item();

        Map<String, Object> metadata = item.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> convertAttributeValue(entry.getValue())
                ));

        return Collections.singletonMap("metadata", metadata);
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
