CREATE TABLE if not exists items(
    item_code varchar NOT NULL PRIMARY KEY,
    name varchar(100),
    description varchar,
    price numeric,
    stock int,
    capacity int
);
