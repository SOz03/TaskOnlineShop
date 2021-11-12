CREATE TABLE customers
(
    id uuid NOT NULL,
    first_name text NOT NULL,
    phone_number text NOT NULL,
    date_birth date,
    address text NOT NULL,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
) TABLESPACE pg_default;
