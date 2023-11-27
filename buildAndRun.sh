#!/bin/bash

DIR_SOURCE="./src/main/java"

if [ ! -d "$DIR_SOURCE" ]; then
  echo "No source directory $DIR_SOURCE"
  exit 1
fi

./gradlew clean build -x test

./gradlew bootRun
