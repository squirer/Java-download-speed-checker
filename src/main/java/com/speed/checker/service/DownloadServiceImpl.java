package com.speed.checker.service;

import com.speed.checker.config.ApplicationProperties;
import com.speed.checker.domain.DownloadReportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DownloadServiceImpl implements DownloadService{

    private static Logger LOG = LoggerFactory.getLogger(DownloadServiceImpl.class.getName());

    @Autowired
    ApplicationProperties applicationProperties;

    @Override
    public DownloadReportDTO runDownloadProcess(OutputStream outputStream) throws IOException {
        LOG.info("Starting download process here");

        Path downloadFilePath = getPathForDownload();
        InputStream inputStream = new FileInputStream(downloadFilePath.toFile());

        // write the data to the output stream, time how long this takes
        byte[] buffer = new byte[applicationProperties.getByteBufferDownloadSize()];
        int bytesRead;
        long startTime = System.nanoTime();
        while((bytesRead = inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, bytesRead);
        }
        long endTime = System.nanoTime();

        return createReportObject(downloadFilePath, startTime, endTime);
    }

    public Path getPathForDownload(){
        String downloadPathProperty = applicationProperties.getDownloadFileLocation();
        if(StringUtils.isEmpty(downloadPathProperty)){
            throw new IllegalArgumentException("Download file property is empty, specify in application.properties with: download.file.location");
        }
        Path downloadFilePath = Paths.get(applicationProperties.getDownloadFileLocation());
        if(!Files.exists(downloadFilePath)){
            throw new IllegalArgumentException("Download file is not present on the server for testing the download speed");
        }
        return downloadFilePath;
    }

    public DownloadReportDTO createReportObject(Path downloadFile, long startTime, long endTime){
        DownloadReportDTO downloadReportDTO = new DownloadReportDTO(downloadFile, startTime, endTime);
        LOG.info("Report: {}", downloadReportDTO.toString());
        return  downloadReportDTO;
    }
}
