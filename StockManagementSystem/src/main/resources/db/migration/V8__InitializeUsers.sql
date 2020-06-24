create table if not exists  users(
    id serial primary key,
    role integer references roles(id),
    username varchar,
    email varchar,
    password varchar
);