-- Table: public."Address"

-- DROP TABLE public."Address";

CREATE TABLE public."Address"
(
    id integer NOT NULL,
    street character varying(30) COLLATE pg_catalog."default",
    "streetNumber" character varying(30) COLLATE pg_catalog."default",
    city character varying(30) COLLATE pg_catalog."default",
    province character varying(30) COLLATE pg_catalog."default",
    "postalCode" character varying(30) COLLATE pg_catalog."default",
    CONSTRAINT "Address_pkey" PRIMARY KEY (id),
    CONSTRAINT "Address_id_fkey" FOREIGN KEY (id)
        REFERENCES public."User" ("userID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public."Address"
    OWNER to xyvyxjqfbebomc;
