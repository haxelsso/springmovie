--
-- PostgreSQL database dump
--


--
-- Name: actor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE actor (
    id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255)
);


ALTER TABLE actor OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- Name: movie; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE movie (
    id bigint NOT NULL,
    name character varying(255),
    year character varying(255)
);


ALTER TABLE movie OWNER TO postgres;

--
-- Name: movie_maker; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE movie_maker (
    id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255)
);


ALTER TABLE movie_maker OWNER TO postgres;

--
-- Data for Name: actor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY actor (id, first_name, last_name) FROM stdin;
1	Matt	Damon
2	Christian	Bale
3	Hugh	Jackman
12	Matt	Damon
13	Christian	Bale
14	Hugh	Jackman
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 14, true);


--
-- Data for Name: movie; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY movie (id, name, year) FROM stdin;
7	Star Wars	2016
8	Star Trek	2016
\.


--
-- Data for Name: movie_maker; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY movie_maker (id, first_name, last_name) FROM stdin;
4	JJ	Abrams
5	Peter	Jackson
6	Luc	Besson
9	JJ	Abrams
10	Peter	Jackson
11	Luc	Besson
\.


--
-- Name: actor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actor
    ADD CONSTRAINT actor_pkey PRIMARY KEY (id);


--
-- Name: movie_maker_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY movie_maker
    ADD CONSTRAINT movie_maker_pkey PRIMARY KEY (id);


--
-- Name: movie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY movie
    ADD CONSTRAINT movie_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

