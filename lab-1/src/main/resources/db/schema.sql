CREATE TYPE user_role AS ENUM ('USER', 'ADMIN');

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL,
    role     user_role    NOT NULL DEFAULT 'USER'
);

CREATE TABLE posts
(
    id         SERIAL PRIMARY KEY,
    title      VARCHAR(255) NOT NULL,
    content    TEXT         NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

INSERT INTO posts (title, content)
VALUES ('First Post', 'This is the content of the first post.');

INSERT INTO posts (title, content)
VALUES ('Second Post', 'Here is some more text for the second post.');

INSERT INTO posts (title, content)
VALUES ('Third Post', 'The third post contains even more detailed content.');