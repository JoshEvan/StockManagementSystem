# set up db di docker sebelum pake flyway migrate

```
C:\Users\Joshua Evan Arijanto>docker run --name joseph-sm-postgres -e POSTGRES_PASSWORD=joseph-sm-postgres -d -p 5432:5432 postgres:alpine
b23ab40ae6f373dcac419524e68ad6675978c6460a0bbb5b007b6e51d685907c

C:\Users\Joshua Evan Arijanto>docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
b23ab40ae6f3        postgres:alpine     "docker-entrypoint.s…"   19 seconds ago      Up 18 seconds       0.0.0.0:5432->5432/tcp   joseph-sm-postgres

C:\Users\Joshua Evan Arijanto>docker exec -it b23ab40ae6f3 /bin/bash
bash-5.0# psql -U postgres -h localhost
psql (12.2)
Type "help" for help.

postgres=# \conninfo
You are connected to database "postgres" as user "postgres" on host "localhost" (address "127.0.0.1") at port "5432".
postgres=# CREATE DATABASE joseph_data;
CREATE DATABASE
postgres=# CREATE SCHEMA joseph;
CREATE SCHEMA
postgres=# \c joseph_data ;
You are now connected to database "joseph_data" as user "postgres".
joseph_data=# set search_path TO joseph;
SET
joseph_data=# CREATE USER joseph_user;
CREATE ROLE
joseph_data=# GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA joseph TO joseph_user;
ERROR:  schema "joseph" does not exist
joseph_data=#  set search_path  to public;
SET
joseph_data=# GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA joseph TO joseph_user;
ERROR:  schema "joseph" does not exist
joseph_data=# \dn
  List of schemas
  Name  |  Owner
--------+----------
 public | postgres
(1 row)

joseph_data=# \l
                                  List of databases
    Name     |  Owner   | Encoding |  Collate   |   Ctype    |   Access privileges
-------------+----------+----------+------------+------------+-----------------------
 joseph_data | postgres | UTF8     | en_US.utf8 | en_US.utf8 |
 postgres    | postgres | UTF8     | en_US.utf8 | en_US.utf8 |
 template0   | postgres | UTF8     | en_US.utf8 | en_US.utf8 | =c/postgres          +
             |          |          |            |            | postgres=CTc/postgres
 template1   | postgres | UTF8     | en_US.utf8 | en_US.utf8 | =c/postgres          +
             |          |          |            |            | postgres=CTc/postgres
(4 rows)

joseph_data=# \c joseph_data
You are now connected to database "joseph_data" as user "postgres".
joseph_data=# \dn
  List of schemas
  Name  |  Owner
--------+----------
 public | postgres
(1 row)

joseph_data=# \q
bash-5.0# psql -U postgres -h localhost
psql (12.2)
Type "help" for help.

postgres=# CREATE SCHEMA joseph;
ERROR:  schema "joseph" already exists
postgres=# GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA joseph TO joseph_user;
GRANT
postgres=# \q
bash-5.0# psql -h localhost -d joseph_data -u joseph_user;
psql: unrecognized option: u
Try "psql --help" for more information.
bash-5.0# psql -h localhost -d joseph_data -U joseph_user
psql (12.2)
Type "help" for help.

joseph_data=> \password
Enter new password:
Enter it again:
joseph_data=>
```
