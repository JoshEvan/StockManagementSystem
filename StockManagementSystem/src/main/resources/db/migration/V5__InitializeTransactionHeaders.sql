CREATE TABLE if not exists transaction_headers(
    id varchar PRIMARY KEY NOT NULL,
    customer_id varchar REFERENCES customers(id),
    payment_id varchar REFERENCES payments(id),
    transaction_date DATE,
    payment_status varchar,
    note varchar,
    timestamp timestamp default current_timestamp,
    is_active boolean DEFAULT TRUE
);