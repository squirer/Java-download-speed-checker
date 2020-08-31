package com.speed.checker.controller;

import com.speed.checker.config.ApplicationProperties;
import com.speed.checker.domain.DownloadReportDTO;
import com.speed.checker.service.DownloadService;
import com.speed.checker.service.DownloadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DownloadRestController {

    private static Logger LOG = LoggerFactory.getLogger(DownloadServiceImpl.class.getName());

    List<DownloadReportDTO> reportsList = new ArrayList<>();

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    DownloadService downloadService;

    @GetMapping("/welcome")
    public String testWelcomeEndpoint(){
        return "Application accessible!";
    }

    @GetMapping("/download")
    public void downloadTest(HttpServletResponse response) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        DownloadReportDTO downloadReportDTO = downloadService.runDownloadProcess(outputStream);
        reportsList.add(downloadReportDTO);
    }

    @GetMapping("/report")
    public DownloadReportDTO getLastReport(){
        return reportsList.get(reportsList.size()-1);
    }
}
