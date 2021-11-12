CREATE TABLE delivery
(
    id uuid NOT NULL,
    name text NOT NULL,
    cost numeric,
    time_delivery time without time zone,
    CONSTRAINT delivery_pkey PRIMARY KEY (id)
) TABLESPACE pg_default;