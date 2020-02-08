-- Table: public."Owner"

-- DROP TABLE public."Owner";

CREATE TABLE public."Owner"
(
    "ownerID" integer NOT NULL,
    CONSTRAINT "Owner_pkey" PRIMARY KEY ("ownerID"),
    CONSTRAINT "Owner_ownerID_fkey" FOREIGN KEY ("ownerID")
        REFERENCES public."User" ("userID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "Owner_ownerID_fkey1" FOREIGN KEY ("ownerID")
        REFERENCES public."Shelter" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public."Owner"
    OWNER to xyvyxjqfbebomc;
