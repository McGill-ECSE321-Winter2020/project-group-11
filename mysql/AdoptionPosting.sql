-- Table: public."AdoptionPosting"

-- DROP TABLE public."AdoptionPosting";

CREATE TABLE public."AdoptionPosting"
(
    id integer NOT NULL,
    CONSTRAINT "AdoptionPosting_pkey" PRIMARY KEY (id),
    CONSTRAINT "AdoptionPosting_id_fkey" FOREIGN KEY (id)
        REFERENCES public."Adopter" ("adopterID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "AdoptionPosting_id_fkey1" FOREIGN KEY (id)
        REFERENCES public."Pet" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public."AdoptionPosting"
    OWNER to xyvyxjqfbebomc;
