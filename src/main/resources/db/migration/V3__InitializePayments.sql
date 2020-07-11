CREATE TABLE if not exists payments(
    id varchar PRIMARY KEY NOT NULL,
    payment_type VARCHAR,
    timestamp timestamp default current_timestamp,
    is_active boolean DEFAULT TRUE
);