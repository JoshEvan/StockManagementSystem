drop table if exists customers;
create table customers(
    id varchar PRIMARY KEY NOT NULL,
    name varchar,
    description varchar,
    contact varchar
);