package com.speed.checker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationProperties {

    @Value("${download.file.location}")
    private String downloadFileLocation;

    @Value("${byte.buffer.download.size}")
    private int byteBufferDownloadSize;

    public String getDownloadFileLocation() {
        return downloadFileLocation;
    }

    public void setDownloadFileLocation(String downloadFileLocation) {
        this.downloadFileLocation = downloadFileLocation;
    }

    public int getByteBufferDownloadSize() {
        return byteBufferDownloadSize;
    }

    public void setByteBufferDownloadSize(int byteBufferDownloadSize) {
        this.byteBufferDownloadSize = byteBufferDownloadSize;
    }
}
