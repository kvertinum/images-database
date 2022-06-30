CREATE TABLE IF NOT EXISTS album (
    id serial primary key,
    name varchar(25) unique,
    password varchar(50)
)

CREATE UNIQUE INDEX idx_album_name ON album(name);
