package com.speed.checker.controller;

import com.speed.checker.config.ApplicationProperties;
import com.speed.checker.domain.DownloadReportDTO;
import com.speed.checker.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class DownloadRestController {

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    DownloadService downloadService;

    @GetMapping("/welcome")
    public String testWelcomeEndpoint(){
        return "Application accessible!";
    }

    @GetMapping("/download")
    public DownloadReportDTO downloadTest(HttpServletResponse response) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        DownloadReportDTO downloadReportDTO = downloadService.runDownloadProcess(outputStream);
        return downloadReportDTO;
    }
}
