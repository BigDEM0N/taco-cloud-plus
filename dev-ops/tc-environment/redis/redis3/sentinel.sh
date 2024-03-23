#!/bin/bash
echo "===Start Redis Sentinel==="
mkdir -p "/usr/tmp/redis"
cp /usr/local/etc/redis/sentinel.conf /usr/tmp/redis/sentinel.conf

redis-sentinel /usr/tmp/redis/sentinel.conf