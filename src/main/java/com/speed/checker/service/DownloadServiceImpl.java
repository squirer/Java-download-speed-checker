package com.speed.checker.service;

import com.speed.checker.domain.DownloadReportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DownloadServiceImpl implements DownloadService{

    private static Logger LOG = LoggerFactory.getLogger(DownloadServiceImpl.class.getName());

    @Override
    public DownloadReportDTO runDownloadProcess() {
        LOG.info("Starting download process here");

        // Create the report object that will hold our info, ideally pass it the file and it does as much
        // property setting as possible?
        DownloadReportDTO downloadReportDTO = new DownloadReportDTO();

        // read file from server here

        // write the file out to client

        // time this process

        // return some kind of report object? Maybe file size used in Mb, time taken, and a Download speed
    }
}
