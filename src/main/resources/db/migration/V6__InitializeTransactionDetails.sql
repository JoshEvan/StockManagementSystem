CREATE TABLE if not exists transaction_details(
    transaction_header_id varchar NOT NULL REFERENCES transaction_headers(id),
    item_code varchar NOT NULL REFERENCES items(item_code),
    price numeric,
    quantity int,
    note varchar,
    timestamp timestamp default current_timestamp,
    is_active boolean DEFAULT TRUE
);