CREATE TABLE if not exists productions(
    id varchar NOT NULL PRIMARY KEY,
    item_code varchar REFERENCES items(item_code),
    production_date DATE,
    producer varchar,
    quantity int,
    timestamp timestamp default current_timestamp,
    is_active boolean DEFAULT TRUE
);