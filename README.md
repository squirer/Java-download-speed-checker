# Quick start: Testing the already deployed server


To test my deployed application:

- Past the download url into your web browser
- When this download completes, paste the report url
- View the download report


    Download GET Request:
    
        http://178.79.162.105:7777/download
        
    (wait until above download completes)
    
        
    Report GET Request
    
        http://178.79.162.105:7777/report



## Background

This project is set up as a Maven Spring Boot project and builds a Java application to run on a server
based on the assembly xml here: 

    src/main/deploy/assembly/assembly.xml

    
folder structure after extraction:

    bin
        start.sh
    config
        application.properties
    lib 
        Java-download-speed-checker-1.0.jar
    logs
        (logs will be here)


## To Run the app yourself 

To run the app on a server - unzip the distribution zip from the target folder, go to the bin folder inside and execute:

    nohup ./start.sh &
    
(Starts the java process in background mode)

You can then tail the logs:

    tail -f nohup.out


## Accessing the web server

To access the webserver and make sure you have access - go to the IP address and use the welcome controller

    http://178.79.162.105:7777/welcome


## Kickoff the download test

To start the download test:

    http://178.79.162.105:7777/download


## Retrieve the report after the above download completes

    http://178.79.162.105:7777/report    



## Initial Server setup:

I setup a linode machine for testing this application - Linode is basically a cloud service provider 
(alternative to AWS) Password will be provided later  

    ssh ubuntu@178.79.162.105
    
Default update steps and install Java 11 + unzip

    sudo apt-get update 

    sudo apt-get upgrade

    sudo apt-get install openjdk-11-jre openjdk-11-jdk
    
    sudo apt-get install unzip


## Deploy scripts

Deploy scripts to copy the app and some download data to the dedicated server:

    src/main/deploy/deployToServer


    
    



