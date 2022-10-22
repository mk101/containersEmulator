CREATE TABLE containers (
    message_id  SERIAL NOT NULL CONSTRAINT containers_pk PRIMARY KEY,
    number      INT NOT NULL,
    ts          timestamp with time zone NOT NULL
);

CREATE TABLE sensors (
    number INT NOT NULL,
    sensor_value DECIMAL NOT NULL,
    message_id INT NOT NULL REFERENCES containers(message_id),
    PRIMARY KEY (number, message_id)
);