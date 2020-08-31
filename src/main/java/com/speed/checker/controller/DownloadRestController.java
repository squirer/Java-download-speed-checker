package com.speed.checker.controller;

import com.speed.checker.domain.DownloadReportDTO;
import com.speed.checker.service.DownloadService;
import com.speed.checker.service.DownloadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class DownloadRestController {

    private static Logger LOG = LoggerFactory.getLogger(DownloadServiceImpl.class.getName());

    @Autowired
    DownloadService downloadService;

    @GetMapping("/welcome")
    public String testWelcomeEndpoint(){
        return "Application accessible!";
    }

    @GetMapping("/download")
    public void downloadTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OutputStream outputStream = response.getOutputStream();

        String clientRemoteAddress = request.getRemoteAddr();
        downloadService.runDownloadProcess(clientRemoteAddress, outputStream);
    }

    @GetMapping("/report")
    public DownloadReportDTO getLastDownloadReport(HttpServletRequest request){
        return downloadService.getLastDownloadReportForIp(request.getRemoteAddr());
    }
}
