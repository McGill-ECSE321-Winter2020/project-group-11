-- Table: public."Manager"

-- DROP TABLE public."Manager";

CREATE TABLE public."Manager"
(
    "managerID" integer NOT NULL,
    CONSTRAINT "Manager_pkey" PRIMARY KEY ("managerID"),
    CONSTRAINT "Manager_managerID_fkey" FOREIGN KEY ("managerID")
        REFERENCES public."User" ("userID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public."Manager"
    OWNER to xyvyxjqfbebomc;
