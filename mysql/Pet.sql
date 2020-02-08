-- Table: public."Pet"

-- DROP TABLE public."Pet";

CREATE TABLE public."Pet"
(
    id integer NOT NULL,
    name character varying(20) COLLATE pg_catalog."default",
    type character varying(200) COLLATE pg_catalog."default",
    CONSTRAINT "Pet_pkey" PRIMARY KEY (id),
    CONSTRAINT "Pet_id_fkey" FOREIGN KEY (id)
        REFERENCES public."Owner" ("ownerID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public."Pet"
    OWNER to xyvyxjqfbebomc;
