CREATE TABLE image
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    review_id BIGINT REFERENCES review (id) ON DELETE CASCADE NOT NULL,
    path      VARCHAR(255)                                    NOT NULL
);