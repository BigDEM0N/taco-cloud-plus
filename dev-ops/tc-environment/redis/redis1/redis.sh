#!/bin/bash
echo "===Start Redis Sentinel==="
mkdir -p "/usr/tmp/redis"
cp /usr/local/etc/redis/redis.conf /usr/tmp/redis/redis.conf

redis-server /usr/tmp/redis/redis.conf