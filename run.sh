#!/bin/bash

mvn clean install test
cd target
java -jar cipherVigener-1.0.jar