#!/bin/bash

docker build -f Dockerfile_oracle -t orakle:test .
docker run --rm -d -p 1521:1521 --name orakletesti docker.io/library/orakle:test
