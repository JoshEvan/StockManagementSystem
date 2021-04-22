CREATE TYPE if not exists user_role as ENUM('STRANGER', 'MEMBER', 'ADMIN');

CREATE TABLE if not exists users(
    username varchar NOT NULL PRIMARY KEY,
    password varchar,
    role user_role,
    is_account_non_expired boolean DEFAULT TRUE,
    is_account_non_locked boolean DEFAULT TRUE,
    is_credential_non_expired boolean DEFAULT TRUE,
    is_enabled boolean DEFAULT TRUE
);
