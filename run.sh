#!/bin/bash

mvn clean install
cd target
java -jar cipherVigener-1.0-SNAPSHOT.jar