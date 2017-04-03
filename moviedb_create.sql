--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.6
-- Dumped by pg_dump version 9.5.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: actmov; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE actmov (
    actorid integer NOT NULL,
    movieid integer NOT NULL
);


ALTER TABLE actmov OWNER TO postgres;

--
-- Name: actor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE actor (
    actorid integer NOT NULL,
    actorforename character(25) NOT NULL,
    actorlastname character(25) NOT NULL
);


ALTER TABLE actor OWNER TO postgres;

--
-- Name: movie; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE movie (
    movieid integer NOT NULL,
    name character(25),
    year integer
);


ALTER TABLE movie OWNER TO postgres;

--
-- Name: moviemaker; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE moviemaker (
    makerid integer NOT NULL,
    makerforename character(25) NOT NULL,
    makerlastname character(25) NOT NULL
);


ALTER TABLE moviemaker OWNER TO postgres;

--
-- Name: movmak; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE movmak (
    makerid integer NOT NULL,
    movieid integer NOT NULL
);


ALTER TABLE movmak OWNER TO postgres;

--
-- Data for Name: actmov; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY actmov (actorid, movieid) FROM stdin;
1	1
2	2
3	3
4	4
5	5
6	6
7	7
8	8
9	9
10	10
\.


--
-- Data for Name: actor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY actor (actorid, actorforename, actorlastname) FROM stdin;
1	Uma                      	Thurman                  
2	Chris                    	Pine                     
3	Matt                     	Damon                    
4	Daisy                    	Ridley                   
5	Chris                    	Pratt                    
6	Leonardo                 	Di Caprio                
7	Christian                	Bale                     
8	Matt                     	Damon                    
9	Hugh                     	Jackman                  
10	Keaneu                   	Reeves                   
\.


--
-- Data for Name: movie; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY movie (movieid, name, year) FROM stdin;
1	Pulp Fiction             	2001
2	Star Trek Beyond         	2016
3	The Martian              	2015
4	Star Wars                	2015
5	Jurassic World           	2014
6	Inception                	2010
7	Batman begins            	2008
8	Jason Bourne             	2016
9	X Men                    	2016
10	Matrix                   	2005
11	Matrix 2                 	2006
12	Spiderman                	2017
13	Matrix 3                 	2007
\.


--
-- Data for Name: moviemaker; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY moviemaker (makerid, makerforename, makerlastname) FROM stdin;
1	Quentin                  	Tarantino                
2	JJ                       	Abrams                   
3	Ridley                   	Scott                    
4	JJ                       	Abrams                   
5	Frank                    	Marshall                 
6	Christopher              	Nolan                    
7	Christopher              	Nolan                    
8	Frank                    	Marshall                 
9	Bryan                    	Singer                   
10	Lana and Lilly           	Wachowski Brothers       
\.


--
-- Data for Name: movmak; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY movmak (makerid, movieid) FROM stdin;
1	1
2	2
3	3
4	4
5	5
6	6
7	7
8	8
9	9
10	10
\.


--
-- Name: actor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actor
    ADD CONSTRAINT actor_pkey PRIMARY KEY (actorid);


--
-- Name: movie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY movie
    ADD CONSTRAINT movie_pkey PRIMARY KEY (movieid);


--
-- Name: moviemaker_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY moviemaker
    ADD CONSTRAINT moviemaker_pkey PRIMARY KEY (makerid);


--
-- Name: actmov_actorid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actmov
    ADD CONSTRAINT actmov_actorid_fkey FOREIGN KEY (actorid) REFERENCES actor(actorid);


--
-- Name: actmov_movieid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actmov
    ADD CONSTRAINT actmov_movieid_fkey FOREIGN KEY (movieid) REFERENCES movie(movieid);


--
-- Name: movmak_makerid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY movmak
    ADD CONSTRAINT movmak_makerid_fkey FOREIGN KEY (makerid) REFERENCES moviemaker(makerid);


--
-- Name: movmak_movieid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY movmak
    ADD CONSTRAINT movmak_movieid_fkey FOREIGN KEY (movieid) REFERENCES movie(movieid);


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

