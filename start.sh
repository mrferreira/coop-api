#!/bin/bash
./mvnw clean package -DskipTests
docker-compose up --force-recreate
