-- Table: public."Shelter"

-- DROP TABLE public."Shelter";

CREATE TABLE public."Shelter"
(
    id integer NOT NULL,
    CONSTRAINT "Shelter_pkey" PRIMARY KEY (id),
    CONSTRAINT "Shelter_id_fkey" FOREIGN KEY (id)
        REFERENCES public."Manager" ("managerID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public."Shelter"
    OWNER to xyvyxjqfbebomc;
