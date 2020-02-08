-- Table: public."User"

-- DROP TABLE public."User";

CREATE TABLE public."User"
(
    "userID" integer NOT NULL,
    username character varying(18) COLLATE pg_catalog."default",
    password character varying(16) COLLATE pg_catalog."default",
    "phoneNumer" character varying(20) COLLATE pg_catalog."default",
    "lastName" character varying(20) COLLATE pg_catalog."default",
    "emailAddress" character varying(30) COLLATE pg_catalog."default",
    description character varying(200) COLLATE pg_catalog."default",
    "firstName" character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT "User_pkey" PRIMARY KEY ("userID")
)

TABLESPACE pg_default;

ALTER TABLE public."User"
    OWNER to xyvyxjqfbebomc;
