package com.group113.swiftify;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GeneratePresignedUrlRequest;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class MetadataFunction implements RequestHandler<Integer, Map<String, Object>> {

    private final DynamoDbClient dynamoDbClient;
    private final S3Client s3Client;
    private final String TABLE_NAME = "MusicMetadata"; // Name of your DynamoDB table
    private final String BUCKET_NAME = "csc207swiftify";

    public MetadataFunction() {
        dynamoDbClient = DependencyFactory.dynamoDbClient();
        s3Client = DependencyFactory.s3Client();
    }

    @Override
    public Map<String, Object> handleRequest(final Integer songId, final Context context) {
        // Query DynamoDB to fetch song metadata
        Map<String, String> key = new HashMap<>();
        key.put("songId", String.valueOf(songId));

        GetItemRequest request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();

        Map<String, Object> item = dynamoDbClient.getItem(request).item();

        // Generate a pre-signed URL for the song in S3
        String s3Key = (String) item.get("s3Key");
        GeneratePresignedUrlRequest generatePresignedUrlRequest = GeneratePresignedUrlRequest.builder()
                .bucket(BUCKET_NAME)
                .key(s3Key)
                .expiresIn(Duration.ofMinutes(15))
                .build();

        URL presignedUrl = s3Client.utilities().generatePresignedUrl(generatePresignedUrlRequest);

        // Add the pre-signed URL to the metadata and return
        item.put("presignedUrl", presignedUrl.toString());
        return item;
    }
}