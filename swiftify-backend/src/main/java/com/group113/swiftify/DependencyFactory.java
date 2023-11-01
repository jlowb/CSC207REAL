package com.group113.swiftify;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3Client;

public class DependencyFactory {

    private static DynamoDbClient dynamoDbClient = null;
    private static S3Client s3Client = null;

    public static DynamoDbClient dynamoDbClient() {
        if (dynamoDbClient == null) {
            dynamoDbClient = DynamoDbClient.builder().build();
        }
        return dynamoDbClient;
    }

    public static S3Client s3Client() {
        if (s3Client == null) {
            s3Client = S3Client.builder().build();
        }
        return s3Client;
    }
}