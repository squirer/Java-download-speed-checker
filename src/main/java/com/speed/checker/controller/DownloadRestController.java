package com.speed.checker.controller;

import com.speed.checker.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class DownloadRestController {

    @Autowired
    DownloadService downloadService;

    @GetMapping("/welcome")
    public String testWelcomeEndpoint(){
        return "Application accessible!";
    }

    @GetMapping
    public void downloadTest(HttpServletResponse response) throws IOException {
        downloadService.runDownloadProcess();

        String filePath = "/some/path";
        InputStream inputStream = new FileInputStream(new File(filePath));

        byte[] buffer = new byte[1024];
        OutputStream outputStream = response.getOutputStream();
        int bytesRead;
        while((bytesRead = inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, bytesRead);
        }

        // ok so at this point we will have finished transferring data to the response

        // return report object to client here ?
    }
}
