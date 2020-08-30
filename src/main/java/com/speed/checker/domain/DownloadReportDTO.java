package com.speed.checker.domain;

public class DownloadReportDTO {

    private String fileName;
    private long fileSizeMb;
    private long timeTakenSeconds;

    public DownloadReportDTO(){

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSizeMb() {
        return fileSizeMb;
    }

    public void setFileSizeMb(long fileSizeMb) {
        this.fileSizeMb = fileSizeMb;
    }

    public long getTimeTakenSeconds() {
        return timeTakenSeconds;
    }

    public void setTimeTakenSeconds(long timeTakenSeconds) {
        this.timeTakenSeconds = timeTakenSeconds;
    }
}
