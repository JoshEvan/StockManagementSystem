create table if not exists  users(
    username varchar unique primary key,
    password varchar,
    role varchar default stranger,
    is_account_non_expired boolean default true,
    is_account_non_locked boolean default true,
    is_credential_non_expired boolean default true,
    is_enabled boolean default true,
);