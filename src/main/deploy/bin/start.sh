#!/bin/bash

## Entry point to our application

java -Xms1G -Xmx2G -server -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=../logs -Dspring.config.location=../config/application.properties -jar ../lib/Java-download-speed-checker-1.0.jar