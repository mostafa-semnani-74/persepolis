
CREATE TABLE IF NOT EXISTS persepolis.product
(
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id),
    CONSTRAINT product_name_key UNIQUE (name)
)

CREATE SEQUENCE IF NOT EXISTS persepolis.seq_product
    INCREMENT 1
    START 1