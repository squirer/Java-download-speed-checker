package com.speed.checker.domain;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class DownloadReportDTO {

    private Path downloadFile;
    private long startTime;
    private long endTime;

    // calculated properties
    private long fileSizeBytes;
    private long fileSizeMb;
    private long durationMilliseconds;
    private long durationSeconds;

    private long speedBytesPerSecond;
    private long speedMbPerSecond;


    public DownloadReportDTO(Path downloadFile, long startTime, long endTime){
        this.downloadFile = downloadFile;
        this.startTime = startTime;
        this.endTime = endTime;
        this.runCalculations();
    }

    private void runCalculations(){
        File underlyingFile = this.downloadFile.toFile();
        this.fileSizeBytes = underlyingFile.length();
        this.fileSizeMb = this.fileSizeBytes / (1024 * 1024);
        this.durationMilliseconds = this.endTime - this.startTime;
        this.durationSeconds = TimeUnit.SECONDS.convert(this.durationMilliseconds, TimeUnit.NANOSECONDS);
        this.speedBytesPerSecond = this.fileSizeBytes / this.durationSeconds;
        this.speedMbPerSecond = this.fileSizeMb / this.durationSeconds;
    }

    @Override
    public String toString(){
        return "downloadFile: " + this.downloadFile + ", Time(s): " + this.durationSeconds +
                ", Size(Mb): " + this.fileSizeMb + ", Speed(Mb/s): " + this.speedMbPerSecond;
    }

}
