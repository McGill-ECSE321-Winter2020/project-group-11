-- Table: public."PetProfile"

-- DROP TABLE public."PetProfile";

CREATE TABLE public."PetProfile"
(
    description character varying(200) COLLATE pg_catalog."default",
    id integer NOT NULL,
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
