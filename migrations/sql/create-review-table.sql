CREATE TABLE review
(
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id        BIGINT REFERENCES users (id) ON DELETE CASCADE       NOT NULL,
    review_item_id BIGINT REFERENCES review_item (id) ON DELETE CASCADE NOT NULL,
    rating         SMALLINT                                             NOT NULL CHECK (rating >= 1 AND rating <= 5),
    advantages     VARCHAR(1000),
    disadvantages  VARCHAR(1000),
    note           VARCHAR(1000)
);