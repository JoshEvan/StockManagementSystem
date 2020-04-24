CREATE TABLE if not exists transaction_headers(
    id varchar PRIMARY KEY,
    customer_id varchar REFERENCES ON customers(id),
    payment_id varchar REFERENCES ON payments(id),
    transaction_data DATE,
    payment_status varchar
);