-- Table: public."PetProfile"

-- DROP TABLE public."PetProfile";

CREATE TABLE public."PetProfile"
(
    id integer NOT NULL,
    description character varying(200) COLLATE pg_catalog."default",
    "photoURL" character varying(200) COLLATE pg_catalog."default",
    CONSTRAINT "PetProfile_pkey" PRIMARY KEY (id),
    CONSTRAINT "PetProfile_id_fkey" FOREIGN KEY (id)
        REFERENCES public."Pet" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public."PetProfile"
    OWNER to xyvyxjqfbebomc;
