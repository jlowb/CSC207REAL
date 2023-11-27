package com.group113.swiftify;

import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class FetchSongHandler {

    public Map<String, Object> handleRequest(Map<String, Object> input) {
        S3Presigner s3Presigner = DependencyFactory.s3Presigner();

        // Extract pathParameters from input and then the SongID
        Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
        String songId = pathParameters.get("SongID");

        // Construct the S3 key using the SongID
        String s3Key = songId + ".mp3";

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("csc207swiftify")
                .key(s3Key)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofHours(1))
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(presignRequest);

        String url = presignedGetObjectRequest.url().toString();

        Map<String, Object> response = new HashMap<>();
        response.put("presignedUrl", url);

        return response;
    }
}