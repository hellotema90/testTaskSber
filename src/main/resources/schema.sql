CREATE TABLE IF NOT EXISTS BUILDINGS
    (
    id                      BIGINT  GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    name                    VARCHAR                                              NOT NULL,
    number_of_floors        BIGINT                                               NOT NULL
    );