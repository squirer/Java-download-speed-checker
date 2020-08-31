package com.speed.checker.Repository;

import com.speed.checker.domain.DownloadReportDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class DownloadReportRepositoryImpl implements DownloadReportRepository {

    HashMap<String, DownloadReportDTO> savedReports = new HashMap<>();

    @Override
    public void saveDownloadReport(DownloadReportDTO downloadReportDTO) {
        savedReports.put(downloadReportDTO.getClientIp(), downloadReportDTO);
    }

    @Override
    public DownloadReportDTO getDownloadReportForIp(String ip) {
        return savedReports.get(ip);
    }
}
