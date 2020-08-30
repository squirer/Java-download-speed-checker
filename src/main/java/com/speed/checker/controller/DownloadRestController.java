package com.speed.checker.controller;

import com.speed.checker.config.ApplicationProperties;
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

    @GetMapping
    public void downloadTest(HttpServletResponse response) throws IOException {



        OutputStream outputStream = response.getOutputStream();
        downloadService.runDownloadProcess(outputStream);



        // ok so at this point we will have finished transferring data to the response

        // return report object to client here ?
    }
}
