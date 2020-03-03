#!/usr/bin/env bash

BASE_DIR="$(dirname $0)"
CLASSPATH="${CLASSPATH}:${BASE_DIR}/assembly/*:${BASE_DIR}/target/classes:${BASE_DIR}/conf"

java -cp "${CLASSPATH}" com.example.chapter9.TweetProducer "$@"
