package com.group113.swiftify.interface_adapter.url_fetch;

import java.net.URL;

public interface S3DataFetcher {
    /**
     * Fetches the URL of an object from Amazon S3.
     *
     * @param bucketName The name of the S3 bucket where the object is stored.
     * @param objectKey  The key (path) of the S3 object.
     * @return The URL of the S3 object, or null if the object doesn't exist.
     */
    URL fetchObjectUrl(String bucketName, String objectKey);
}