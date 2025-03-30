CREATE TABLE car_configuration (
    id BIGSERIAL PRIMARY KEY,
    motorleistung VARCHAR(50),
    lackierung VARCHAR(50),
    felgen VARCHAR(50),
    sonderausstattungen VARCHAR(500),
    price NUMERIC(10, 2)
);

CREATE TABLE ORDERS (
    id BIGSERIAL PRIMARY KEY,
    configuration_id BIGINT NOT NULL,
    order_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_configuration
        FOREIGN KEY (configuration_id) REFERENCES car_configuration(id)
);