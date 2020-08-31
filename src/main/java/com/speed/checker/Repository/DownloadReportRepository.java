package com.speed.checker.Repository;

import com.speed.checker.domain.DownloadReportDTO;

public interface DownloadReportRepository {

    void saveDownloadReport(DownloadReportDTO downloadReportDTO);

    DownloadReportDTO getDownloadReportForIp(String ip);
}
