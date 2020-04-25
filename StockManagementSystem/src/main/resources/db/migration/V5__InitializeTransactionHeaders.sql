CREATE TABLE if not exists transaction_headers(
    id varchar PRIMARY KEY NOT NULL,
    customer_id varchar REFERENCES customers(id),
    payment_id varchar REFERENCES payments(id),
    transaction_data DATE,
    payment_status varchar
);