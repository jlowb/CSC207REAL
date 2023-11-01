package com.group113.swiftify;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

public class DependencyFactory {

    private static DynamoDbClient dynamoDbClient = null;
    private static S3Presigner s3Presigner = null;

    public static DynamoDbClient dynamoDbClient() {
        if (dynamoDbClient == null) {
            dynamoDbClient = DynamoDbClient.builder().build();
        }
        return dynamoDbClient;
    }

    public static S3Presigner s3Presigner() {
        if (s3Presigner == null) {
            s3Presigner = S3Presigner.builder().build();
        }
        return s3Presigner;
    }
}