-- Table: users
CREATE TABLE users
(
  id bigint NOT NULL,
  description character varying(2000),
  first_name character varying(30) NOT NULL,
  last_name character varying(50) NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;
-------------------------------------------------
-- Table: banks
CREATE TABLE banks
  (
    id bigint NOT NULL,
    address character varying(200),
    branch_number character varying(30) NOT NULL,
    name character varying(30) NOT NULL,
    CONSTRAINT banks_pkey PRIMARY KEY (id)
  )
  WITH (
    OIDS=FALSE
  );
  ALTER TABLE banks
    OWNER TO postgres;
-------------------------------------------------
-- Table: accounts
CREATE TABLE accounts
  (
    id bigint NOT NULL,
    account_number character varying(30) NOT NULL,
    balance numeric(19,2) NOT NULL,
    bank_id bigint,
    user_id bigint,
    CONSTRAINT accounts_pkey PRIMARY KEY (id),
    CONSTRAINT fkb78evw9x9jyy66ld572kl8rgx FOREIGN KEY (bank_id)
        REFERENCES banks (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fknjuop33mo69pd79ctplkck40n FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
  )
  WITH (
    OIDS=FALSE
  );
  ALTER TABLE accounts
    OWNER TO postgres;
-------------------------------------------------
-- Table: cards
CREATE TABLE cards
(
  id bigint NOT NULL,
  card_number character varying(16) NOT NULL,
  expiration_date timestamp without time zone NOT NULL,
  pin character varying(4) NOT NULL,
  try_with_error integer NOT NULL,
  active boolean NOT NULL,
  account_id bigint,
  CONSTRAINT cards_pkey PRIMARY KEY (id),
  CONSTRAINT fkdjw7dinkpc0f01yk4m57uq2u2 FOREIGN KEY (account_id)
      REFERENCES accounts (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cards
  OWNER TO postgres;
-------------------------------------------------
-- Table: atms
CREATE TABLE atms
(
  id bigint NOT NULL,
  atm_number character varying(10) NOT NULL,
  available_cash numeric(19,2) NOT NULL,
  CONSTRAINT atms_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE atms
  OWNER TO postgres;


