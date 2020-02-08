-- Table: public."Adopter"

-- DROP TABLE public."Adopter";

CREATE TABLE public."Adopter"
(
    "adopterID" integer NOT NULL,
    CONSTRAINT "Adopter_pkey" PRIMARY KEY ("adopterID"),
    CONSTRAINT "Adopter_adopterID_fkey" FOREIGN KEY ("adopterID")
        REFERENCES public."User" ("userID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public."Adopter"
    OWNER to xyvyxjqfbebomc;
