create table customers(
    id varchar PRIMARY KEY NOT NULL,
    name varchar,
    description varchar,
    contact varchar,
    timestamp timestamp default current_timestamp,
    is_active boolean DEFAULT TRUE
);