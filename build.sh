#!/usr/bin/env bash

# first build the frontend

DOCKER_BUILDKIT=1;
export DOCKER_BUILDKIT;

docker build -o frontend/dist  ./frontend/

# then run nginx
docker build -f Dockerfile -t latest . && docker run -p 3000:80 latest