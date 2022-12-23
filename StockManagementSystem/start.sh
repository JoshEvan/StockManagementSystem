#!/bin/sh
export $(cat .env | xargs)
echo ${JDBC_DATABASE_URL}
exec java ${JAVA_OPTS} -jar /app/app.jar ${0}