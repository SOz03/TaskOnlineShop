CREATE TABLE orders
(
    id uuid NOT NULL,
    delivery_id uuid NOT NULL,
    cost numeric,
    date timestamp without time zone,
    status text,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT fk_orders_delivery FOREIGN KEY (delivery_id)
        REFERENCES delivery (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
) TABLESPACE pg_default;