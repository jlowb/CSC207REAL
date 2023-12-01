package com.group113.swiftify;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.AbstractMap.SimpleEntry;

public class MusicMetadataHandler implements RequestHandler<Object, String> {

    DynamoDbClient dynamoDb = DependencyFactory.dynamoDbClient();

    @Override
    public String handleRequest(Object input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Loading Java Lambda handler to retrieve music metadata");

        try {
            // Scan the DynamoDB table
            ScanRequest scanRequest = ScanRequest.builder()
                    .tableName("MusicMetadata")
                    .build();
            ScanResponse scanResponse = dynamoDb.scan(scanRequest);

            // Convert DynamoDB items to a more generic Map format for JSON serialization
            List<Map<String, Object>> simplifiedItems = scanResponse.items().stream()
                    .map(this::convertToSerializableMap)
                    .collect(Collectors.toList());

            // Convert the items to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(simplifiedItems);
        } catch (Exception e) {
            logger.log("Error occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private Map<String, Object> convertToSerializableMap(Map<String, AttributeValue> item) {
        Map<String, Object> orderedMap = new LinkedHashMap<>();

        // First, add the SongID to the map if it exists
        if (item.containsKey("SongID")) {
            orderedMap.put("SongID", convertAttributeValue(item.get("SongID")));
        }

        // Then add the rest of the entries
        item.forEach((key, value) -> {
            if (!key.equals("SongID")) { // Skip adding SongID again
                orderedMap.put(key, convertAttributeValue(value));
            }
        });

        return orderedMap;
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
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> convertAttributeValue(e.getValue())));
        } else if (value.l() != null) {
            return value.l().stream().map(this::convertAttributeValue).collect(Collectors.toList());
        }
        // Add more cases as needed for other data types
        return null;
    }
}

