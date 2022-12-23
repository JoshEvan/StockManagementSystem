FROM postgres:13.1-alpine
COPY init_db.sql /docker-entrypoint-initdb.d/
EXPOSE 5432