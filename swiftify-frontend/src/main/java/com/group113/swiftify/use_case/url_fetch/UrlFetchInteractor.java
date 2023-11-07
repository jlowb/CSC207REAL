package com.group113.swiftify.use_case.url_fetch;

import com.group113.swiftify.interface_adapter.url_fetch.S3DataFetcher;
import java.net.URL;

public class UrlFetchInteractor {
    private S3DataFetcher s3DataFetcher;

    //Constructor
    public UrlFetchInteractor(S3DataFetcher s3DataFetcher) {
        this.s3DataFetcher = s3DataFetcher;
    }

    public URL fetchS3ObjectUrl(String bucketName, String objectKey) {
        // You can add business logic here if needed
        //TODO: Vincent do.

        return s3DataFetcher.fetchObjectUrl(bucketName, objectKey);
    }
}
