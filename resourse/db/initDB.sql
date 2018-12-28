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


-- create table public."clothetypes";

CREATE TABLE public."clothetypes"
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ClothesType_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."clothetypes"
    OWNER to postgres;
--


-- create table public."repairtypes";

CREATE TABLE public."repairtypes"
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ServiceRepairType_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."repairtypes"
    OWNER to postgres;
--


-- create table public."servicedates";

CREATE TABLE public."servicedates"
(
    id integer NOT NULL,
    measurements date,
    modeling date,
    pattern date,
    stitching date,
    complete date,
    CONSTRAINT "servicedates_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."servicedates"
    OWNER to postgres;
--


-- create table public."servicetypes";

CREATE TABLE public."servicetypes"
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ServiceType_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."servicetypes"
    OWNER to postgres;
--


-- create table public."services";

CREATE TABLE public."services"
(
    id integer NOT NULL,
    quantity integer,
    "serviceType_id" integer,
    "serviceDate_id" integer,
    "serviceRepair_id" integer,
    CONSTRAINT "services_pkey" PRIMARY KEY (id),
    CONSTRAINT "repairtypes_fkey" FOREIGN KEY ("serviceRepair_id")
        REFERENCES public."repairtypes" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "servicedates_fkey" FOREIGN KEY ("serviceDate_id")
        REFERENCES public."servicedates" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "servicetypes_fkey" FOREIGN KEY ("serviceType_id")
        REFERENCES public."servicetypes" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."services"
    OWNER to postgres;

CREATE INDEX "fki_repairtypes_fkey"
    ON public."services" USING btree
    ("serviceRepair_id")
    TABLESPACE pg_default;

CREATE INDEX "fki_servicedates_id"
    ON public."services" USING btree
    ("serviceDate_id")
    TABLESPACE pg_default;

CREATE INDEX "fki_servicetypes"
    ON public."services" USING btree
    ("serviceType_id")
    TABLESPACE pg_default;
--


-- create table public."usertypes";

CREATE TABLE public."usertypes"
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default",
    CONSTRAINT "UserType_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."usertypes"
    OWNER to postgres;
--


-- create table public."users";

CREATE TABLE public."users"
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
    CONSTRAINT "users_pkey" PRIMARY KEY (id),
    CONSTRAINT "UserType_fkey" FOREIGN KEY ("userType_id")
        REFERENCES public."usertypes" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."users"
    OWNER to postgres;


CREATE INDEX "fki_UserType_fkey"
    ON public."users" USING btree
    ("userType_id")
    TABLESPACE pg_default;
--


-- create table public."orders";

CREATE TABLE public."orders"
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
    CONSTRAINT "orders_pkey" PRIMARY KEY (id),
    CONSTRAINT "clothetypes_fkey" FOREIGN KEY ("clothesType_id")
        REFERENCES public."clothetypes" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "Service_fkey" FOREIGN KEY (services_id)
        REFERENCES public."services" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "User_client_fkey" FOREIGN KEY (user_id_client)
        REFERENCES public."users" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "User_manager_fkey" FOREIGN KEY (user_id_manager)
        REFERENCES public."users" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "User_tailor_fkey" FOREIGN KEY (user_id_tailor)
        REFERENCES public."users" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."orders"
    OWNER to postgres;

CREATE INDEX "fki_clothetypes_fkey"
    ON public."orders" USING btree
    ("clothesType_id")
    TABLESPACE pg_default;

CREATE INDEX fki_client_fk
    ON public."orders" USING btree
    (user_id_client)
    TABLESPACE pg_default;

CREATE INDEX fki_manager_fk
    ON public."orders" USING btree
    (user_id_manager)
    TABLESPACE pg_default;

CREATE INDEX fki_service_fk
    ON public."orders" USING btree
    (services_id)
    TABLESPACE pg_default;

CREATE INDEX fki_tailor_fk
    ON public."orders" USING btree
    (user_id_tailor)
    TABLESPACE pg_default;
--


-- create table public."prices";

CREATE TABLE public."prices"
(
    id integer NOT NULL,
    "clotherType_id" integer,
    "serviceType_id" integer,
    "repairType_id" integer,
    cost double precision,
    CONSTRAINT "prices_pkey" PRIMARY KEY (id),
    CONSTRAINT "clothetypes_id_fkey" FOREIGN KEY ("clotherType_id")
        REFERENCES public."clothetypes" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "repairtypes_id_fkey" FOREIGN KEY ("repairType_id")
        REFERENCES public."repairtypes" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "servicetypes_id_fkey" FOREIGN KEY ("serviceType_id")
        REFERENCES public."servicetypes" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."prices"
    OWNER to postgres;

CREATE INDEX "fki_clothetypes_id_fkey"
    ON public."prices" USING btree
    ("clotherType_id")
    TABLESPACE pg_default;

CREATE INDEX "fki_repairtypes_id_fkey"
    ON public."prices" USING btree
    ("repairType_id")
    TABLESPACE pg_default;

CREATE INDEX "fki_servicetypes_id_fkey"
    ON public."prices" USING btree
    ("serviceType_id")
    TABLESPACE pg_default;
--