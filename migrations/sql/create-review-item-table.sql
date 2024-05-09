CREATE TABLE review_item
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(200)                                      NOT NULL UNIQUE,
    category_id BIGINT REFERENCES category (id) ON DELETE CASCADE NOT NULL
);