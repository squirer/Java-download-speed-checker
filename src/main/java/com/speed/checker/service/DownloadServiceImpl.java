package com.speed.checker.service;

import com.speed.checker.Repository.DownloadReportRepository;
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

    @Autowired
    DownloadReportRepository downloadReportRepository;

    /**
     * The main download service - responsible for writing the file to the output stream and also building and saving
     * the resulting report to the Repository
     * @param clientRemoteAddress
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void runDownloadProcess(String clientRemoteAddress, OutputStream outputStream) throws IOException {
        LOG.info("Starting download service now...");

        Path downloadFilePath = getPathForDownload();
        InputStream inputStream = new FileInputStream(downloadFilePath.toFile());

        byte[] buffer = new byte[applicationProperties.getByteBufferDownloadSize()];
        int bytesRead;
        long startTime = System.nanoTime();
        while((bytesRead = inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, bytesRead);
        }
        long endTime = System.nanoTime();

        DownloadReportDTO downloadReportDTO = createReportObject(downloadFilePath, startTime, endTime, clientRemoteAddress);
        downloadReportRepository.saveDownloadReport(downloadReportDTO);
    }

    @Override
    public DownloadReportDTO getLastDownloadReportForIp(String clientRemoteAddress) {
        return downloadReportRepository.getDownloadReportForIp(clientRemoteAddress);
    }

    /**
     * Server checks to make sure our test download file exists, throws errors if the property is not set - or if the
     * file is not existing
     * @return
     */
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

    /**
     * Create our download report object
     * @param downloadFile
     * @param startTime
     * @param endTime
     * @param clientIp
     * @return
     */
    public DownloadReportDTO createReportObject(Path downloadFile, long startTime, long endTime, String clientIp){
        DownloadReportDTO downloadReportDTO = new DownloadReportDTO(downloadFile, startTime, endTime, clientIp);
        LOG.info("Report: {}", downloadReportDTO.toString());
        return  downloadReportDTO;
    }
}
