package com.group113.swiftify;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FetchAlbumTitlesHandler {

    public Map<String, Object> handleRequest(Map<String, Object> input) {
        // Initialize DynamoDB client using DependencyFactory
        DynamoDbClient dynamoDb = DependencyFactory.dynamoDbClient();

        // Prepare DynamoDB Scan request to fetch only album titles
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName("MusicMetadata")
                .attributesToGet("album")
                .build();

        // Fetch items from DynamoDB
        ScanResponse scanResponse = dynamoDb.scan(scanRequest);
        List<Map<String, AttributeValue>> items = scanResponse.items();

        // Extract album titles and remove duplicates
        Set<String> albumTitles = items.stream()
                .map(item -> item.get("album").s())
                .collect(Collectors.toSet());

        // Return the response with album titles
        return Collections.singletonMap("albumTitles", albumTitles);
    }
}

