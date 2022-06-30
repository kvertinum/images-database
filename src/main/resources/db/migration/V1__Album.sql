CREATE TABLE IF NOT EXISTS album (
    id serial primary key,
    name varchar(25) unique,
    password varchar(50)
)
