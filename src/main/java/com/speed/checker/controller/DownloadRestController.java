package com.speed.checker.controller;

import com.speed.checker.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadRestController {

    @Autowired
    DownloadService downloadService;

    @GetMapping("/welcome")
    public String testWelcomeEndpoint(){
        return "Application accessible!";
    }

    @GetMapping
    public void downloadTest(){
        downloadService.runDownloadProcess();

        // return report object to client here ?
    }
}
