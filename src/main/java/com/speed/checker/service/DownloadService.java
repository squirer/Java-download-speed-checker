package com.speed.checker.service;

import com.speed.checker.domain.DownloadReportDTO;

import java.io.IOException;
import java.io.OutputStream;

public interface DownloadService {

    DownloadReportDTO runDownloadProcess(OutputStream outputStream) throws IOException;
}
