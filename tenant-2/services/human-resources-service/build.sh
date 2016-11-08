#!/usr/bin/env bash
set -e

echo "Building service..."
mvn clean install

echo "Building docker image..."
cp target/human-resources-service-*.jar docker/
pushd docker/
docker build -t human-resources-service .
popd
