CREATE TABLE products
(
    id uuid NOT NULL,
    name text NOT NULL,
    price numeric NOT NULL,
    production_date date,
    description text NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id)
) TABLESPACE pg_default;