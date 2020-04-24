CREATE TABLE if not exists transaction_details(
    transaction_header_id varchar NOT NULL REFERENCES transaction_headers(id),
    item_code varchar NOT NULL REFERENCES items(item_code),
    price numeric,
    quantity int
);