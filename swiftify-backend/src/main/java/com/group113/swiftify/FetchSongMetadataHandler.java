package com.group113.swiftify;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;

import java.util.Collections;
import java.util.Map;

public class FetchSongMetadataHandler {

    public Map<String, Object> handleRequest(Map<String, Object> input) {
        // Initialize DynamoDB client using DependencyFactory
        DynamoDbClient dynamoDb = DependencyFactory.dynamoDbClient();

        // Fetch SongID from input
        Integer songId = (Integer) input.get("SongID");

        // Prepare DynamoDB GetItem request with the correct AttributeValue
        GetItemRequest getItemRequest = GetItemRequest.builder()
                .tableName("MusicMetadata")
                .key(Collections.singletonMap("SongID", AttributeValue.builder().n(songId.toString()).build()))
                .build();

        // Fetch item from DynamoDB
        GetItemResponse getItemResponse = dynamoDb.getItem(getItemRequest);
        Map<String, AttributeValue> item = getItemResponse.item();

        // Return the response with metadata
        return Collections.singletonMap("metadata", item);
    }
}
