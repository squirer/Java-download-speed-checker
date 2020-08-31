package com.speed.checker.service;

import com.speed.checker.domain.DownloadReportDTO;

import java.io.IOException;
import java.io.OutputStream;

public interface DownloadService {

    void runDownloadProcess(String clientRemoteAddress, OutputStream outputStream) throws IOException;

    DownloadReportDTO getLastDownloadReportForIp(String clientRemoteAddress);
}
