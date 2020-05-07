CREATE TABLE "items" (
  "item_code" varchar PRIMARY KEY,
  "name" varchar,
  "description" varchar,
  "price" float8,
  "stock" int,
  "capacity" int
);

CREATE TABLE "customers" (
  "id" serial PRIMARY KEY,
  "name" varchar,
  "description" varchar,
  "contact" varchar
);

CREATE TABLE "transaction_headers" (
  "id" varchar PRIMARY KEY,
  "customer_id" int,
  "payment_id" int,
  "transaction_date" date,
  "payment_status" varchar
);

CREATE TABLE "payments" (
  "id" serial,
  "payment_type" varchar
);

CREATE TABLE "transaction_details" (
  "id" varchar,
  "item_code" varchar,
  "price" float8,
  "quantity" int
);

CREATE TABLE "productions" (
  "id" int,
  "item_id" varchar,
  "production_date" date,
  "producer_name" varchar,
  "quantity" int
);

ALTER TABLE "transaction_headers" ADD FOREIGN KEY ("customer_id") REFERENCES "customers" ("id");

ALTER TABLE "transaction_headers" ADD FOREIGN KEY ("payment_id") REFERENCES "payments" ("id");

ALTER TABLE "transaction_details" ADD FOREIGN KEY ("id") REFERENCES "transaction_headers" ("id");

ALTER TABLE "transaction_details" ADD FOREIGN KEY ("item_code") REFERENCES "items" ("item_code");

ALTER TABLE "productions" ADD FOREIGN KEY ("item_id") REFERENCES "items" ("item_code");
