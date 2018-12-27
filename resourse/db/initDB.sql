-- create database: studoiDB

CREATE DATABASE "studioDB"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
--


-- create table public."ClotheTypes";

CREATE TABLE public."ClotheTypes"
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ClothesType_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."ClotheTypes"
    OWNER to postgres;
--


-- create table public."RepairTypes";

CREATE TABLE public."RepairTypes"
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ServiceRepairType_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."RepairTypes"
    OWNER to postgres;
--


-- create table public."ServiceDates";

CREATE TABLE public."ServiceDates"
(
    id integer NOT NULL,
    measurements date,
    modeling date,
    pattern date,
    stitching date,
    complete date,
    CONSTRAINT "ServiceDates_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."ServiceDates"
    OWNER to postgres;
--


-- create table public."ServiceTypes";

CREATE TABLE public."ServiceTypes"
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ServiceType_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."ServiceTypes"
    OWNER to postgres;
--


-- create table public."Services";

CREATE TABLE public."Services"
(
    id integer NOT NULL,
    quantity integer,
    "serviceType_id" integer,
    "serviceDate_id" integer,
    "serviceRepair_id" integer,
    CONSTRAINT "Services_pkey" PRIMARY KEY (id),
    CONSTRAINT "RepairTypes_fkey" FOREIGN KEY ("serviceRepair_id")
        REFERENCES public."RepairTypes" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "ServiceDates_fkey" FOREIGN KEY ("serviceDate_id")
        REFERENCES public."ServiceDates" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "ServiceTypes_fkey" FOREIGN KEY ("serviceType_id")
        REFERENCES public."ServiceTypes" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Services"
    OWNER to postgres;

CREATE INDEX "fki_RepairTypes_fkey"
    ON public."Services" USING btree
    ("serviceRepair_id")
    TABLESPACE pg_default;

CREATE INDEX "fki_ServiceDates_id"
    ON public."Services" USING btree
    ("serviceDate_id")
    TABLESPACE pg_default;

CREATE INDEX "fki_ServiceTypes"
    ON public."Services" USING btree
    ("serviceType_id")
    TABLESPACE pg_default;
--


-- create table public."UserTypes";

CREATE TABLE public."UserTypes"
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default",
    CONSTRAINT "UserType_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."UserTypes"
    OWNER to postgres;
--


-- create table public."Users";

CREATE TABLE public."Users"
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default",
    password text COLLATE pg_catalog."default",
    login text COLLATE pg_catalog."default",
    email text COLLATE pg_catalog."default",
    "phoneNumber" text COLLATE pg_catalog."default",
    address text COLLATE pg_catalog."default",
    experience integer,
    "dateOfAdoption" date,
    "userType_id" integer,
    CONSTRAINT "Users_pkey" PRIMARY KEY (id),
    CONSTRAINT "UserType_fkey" FOREIGN KEY ("userType_id")
        REFERENCES public."UserTypes" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Users"
    OWNER to postgres;


CREATE INDEX "fki_UserType_fkey"
    ON public."Users" USING btree
    ("userType_id")
    TABLESPACE pg_default;
--


-- create table public."Orders";

CREATE TABLE public."Orders"
(
    id integer NOT NULL,
    "tailorAssignment" boolean,
    user_id_manager integer,
    user_id_client integer,
    user_id_tailor integer,
    cost double precision,
    services_id integer,
    "clothesType_id" integer,
    complete boolean,
    "givenOut" boolean,
    CONSTRAINT "Orders_pkey" PRIMARY KEY (id),
    CONSTRAINT "ClotheTypes_fkey" FOREIGN KEY ("clothesType_id")
        REFERENCES public."ClotheTypes" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "Service_fkey" FOREIGN KEY (services_id)
        REFERENCES public."Services" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "User_client_fkey" FOREIGN KEY (user_id_client)
        REFERENCES public."Users" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "User_manager_fkey" FOREIGN KEY (user_id_manager)
        REFERENCES public."Users" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "User_tailor_fkey" FOREIGN KEY (user_id_tailor)
        REFERENCES public."Users" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Orders"
    OWNER to postgres;

CREATE INDEX "fki_ClotheTypes_fkey"
    ON public."Orders" USING btree
    ("clothesType_id")
    TABLESPACE pg_default;

CREATE INDEX fki_client_fk
    ON public."Orders" USING btree
    (user_id_client)
    TABLESPACE pg_default;

CREATE INDEX fki_manager_fk
    ON public."Orders" USING btree
    (user_id_manager)
    TABLESPACE pg_default;

CREATE INDEX fki_service_fk
    ON public."Orders" USING btree
    (services_id)
    TABLESPACE pg_default;

CREATE INDEX fki_tailor_fk
    ON public."Orders" USING btree
    (user_id_tailor)
    TABLESPACE pg_default;
--


-- create table public."Prices";

CREATE TABLE public."Prices"
(
    id integer NOT NULL,
    "clotherType_id" integer,
    "serviceType_id" integer,
    "repairType_id" integer,
    cost double precision,
    CONSTRAINT "Prices_pkey" PRIMARY KEY (id),
    CONSTRAINT "ClotheTypes_id_fkey" FOREIGN KEY ("clotherType_id")
        REFERENCES public."ClotheTypes" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "RepairTypes_id_fkey" FOREIGN KEY ("repairType_id")
        REFERENCES public."RepairTypes" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "ServiceTypes_id_fkey" FOREIGN KEY ("serviceType_id")
        REFERENCES public."ServiceTypes" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Prices"
    OWNER to postgres;

CREATE INDEX "fki_ClotheTypes_id_fkey"
    ON public."Prices" USING btree
    ("clotherType_id")
    TABLESPACE pg_default;

CREATE INDEX "fki_RepairTypes_id_fkey"
    ON public."Prices" USING btree
    ("repairType_id")
    TABLESPACE pg_default;

CREATE INDEX "fki_ServiceTypes_id_fkey"
    ON public."Prices" USING btree
    ("serviceType_id")
    TABLESPACE pg_default;
--