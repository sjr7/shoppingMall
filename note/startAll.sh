#! /bin/sh
echo "==========start redis-6379========="
cd redis-6379
./redis-server ./redis.conf
echo "==========start redis-7000========="
cd ..
cd redis-7000
./redis-server ./redis.conf
echo "==========start redis-7001========="
cd ..
cd redis-7001
./redis-server ./redis.conf
echo "==========start redis-7002========="
cd ..
cd redis-7002
./redis-server ./redis.conf
echo "==========start redis-7003========="
cd ..
cd redis-7003
./redis-server ./redis.conf
echo "==========start redis-7004========="
cd ..
cd redis-7004
./redis-server ./redis.conf
echo "==========  all end ========="