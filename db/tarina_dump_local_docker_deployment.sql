--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 14.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


ALTER ROLE tarinam WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS;

CREATE SCHEMA tarinam;


ALTER SCHEMA tarinam OWNER TO tarinam;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: ANTURIKOHTAISETTUNNUSARVOT; Type: TABLE; Schema: tarinam; Owner: tarinam
--

CREATE TABLE tarinam."anturikohtaisettunnusarvot" (
    "tunnusarvo_id" numeric(38,10) NOT NULL,
    "mittaussuunta_xyz" character varying(1),
    "tarinan_maksimiarvo" numeric(38,10),
    "hallitseva_taajuus" numeric(38,10),
    "tarinan_tunnusluku_vw95_rms" numeric(38,10),
    "asennettuanturi" numeric(38,10)
);


ALTER TABLE tarinam."anturikohtaisettunnusarvot" OWNER TO tarinam;

--
-- Name: ASENNETTUANTURI; Type: TABLE; Schema: tarinam; Owner: tarinam
--

CREATE TABLE tarinam."asennettuanturi" (
    "asennuskohtainen_id" numeric(38,10) NOT NULL,
    "malli" character varying(255),
    "gps_lat" numeric(38,10),
    "gps_long" numeric(38,10),
    "etaisyys_radasta_jos_eri" numeric(38,10),
    "kerros" numeric(38,0),
    "sijoituspaikan_lisaselite" character varying(255),
    "mittaus" numeric(38,10),
    "asennuspaikka" numeric(38,10)
);


ALTER TABLE tarinam."asennettuanturi" OWNER TO tarinam;

--
-- Name: ASENNUSPAIKANTYYPPI; Type: TABLE; Schema: tarinam; Owner: tarinam
--

CREATE TABLE tarinam."asennuspaikantyyppi" (
    "paikkatyyppi_id" numeric(38,10) NOT NULL,
    "selite" character varying(10),
    "lisatiedot" character varying(255)
);


ALTER TABLE tarinam."asennuspaikantyyppi" OWNER TO tarinam;

--
-- Name: MITTAUS; Type: TABLE; Schema: tarinam; Owner: tarinam
--

CREATE TABLE tarinam."mittaus" (
    "kohde_id" numeric(38,10) NOT NULL,
    "alkuaika" timestamp without time zone,
    "loppuaika" timestamp without time zone,
    "mittaus_asianhallinta_id" character varying(255),
    "pdf_raportin_linkki" character varying(255),
    "rakennuksen_pinta_ala" numeric(38,10),
    "perustamistapa" character varying(255),
    "julkisivumateriaali" character varying(255),
    "runkomateriaali" character varying(255),
    "rakennusvuosi" numeric(38,0),
    "katuosoite" character varying(255),
    "postinumero" character varying(255),
    "created_by_lx" character varying(255)
);


ALTER TABLE tarinam."mittaus" OWNER TO tarinam;

--
-- Name: ANTURIKOHTAISETTUNNUSARVOT ANTURIKOHTAISETTUNNUSARVOT_pkey; Type: CONSTRAINT; Schema: tarinam; Owner: tarinam
--

ALTER TABLE ONLY tarinam."anturikohtaisettunnusarvot"
    ADD CONSTRAINT "ANTURIKOHTAISETTUNNUSARVOT_pkey" PRIMARY KEY ("tunnusarvo_id");


--
-- Name: ASENNETTUANTURI ASENNETTUANTURI_pkey; Type: CONSTRAINT; Schema: tarinam; Owner: tarinam
--

ALTER TABLE ONLY tarinam."asennettuanturi"
    ADD CONSTRAINT "ASENNETTUANTURI_pkey" PRIMARY KEY ("asennuskohtainen_id");


--
-- Name: ASENNUSPAIKANTYYPPI ASENNUSPAIKANTYYPPI_pkey; Type: CONSTRAINT; Schema: tarinam; Owner: tarinam
--

ALTER TABLE ONLY tarinam."asennuspaikantyyppi"
    ADD CONSTRAINT "ASENNUSPAIKANTYYPPI_pkey" PRIMARY KEY ("paikkatyyppi_id");


--
-- Name: MITTAUS MITTAUS_pkey; Type: CONSTRAINT; Schema: tarinam; Owner: tarinam
--

ALTER TABLE ONLY tarinam."mittaus"
    ADD CONSTRAINT "MITTAUS_pkey" PRIMARY KEY ("kohde_id");


CREATE SEQUENCE tarinam.asennettuanturi_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE SEQUENCE tarinam.mittaus_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE SEQUENCE tarinam.paikka_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE SEQUENCE tarinam.tunnusarvo_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
