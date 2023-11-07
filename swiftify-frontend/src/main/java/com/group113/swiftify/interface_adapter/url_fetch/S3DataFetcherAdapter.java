package com.group113.swiftify.interface_adapter.url_fetch;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.HttpMethod;

import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class S3DataFetcherAdapter implements S3DataFetcher {
    private AmazonS3 s3Client;

    public S3DataFetcherAdapter(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public URL fetchObjectUrl(String bucketName, String objectKey) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey)
                .withMethod(HttpMethod.GET)
                //.withMethod(FetchSongHandler.GET) ??
                .withExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)));

        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

        return url;
    }
}
