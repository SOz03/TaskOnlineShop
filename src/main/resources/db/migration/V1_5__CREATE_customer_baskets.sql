CREATE TABLE customer_baskets
(
    id uuid NOT NULL,
    customers_id uuid,
    CONSTRAINT customer_baskets_pkey PRIMARY KEY (id),
    CONSTRAINT fk_customer_baskets_customer FOREIGN KEY (customers_id)
        REFERENCES customers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
) TABLESPACE pg_default;