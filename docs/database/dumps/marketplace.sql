-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.1-beta
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.com.br
-- Model Author: ---


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: marketplace | type: DATABASE --
-- -- DROP DATABASE IF EXISTS marketplace;
-- CREATE DATABASE marketplace
-- 	ENCODING = 'UTF8'
-- 	LC_COLLATE = 'en_US.utf8'
-- 	LC_CTYPE = 'en_US.utf8'
-- 	TABLESPACE = pg_default
-- 	OWNER = postgres
-- ;
-- -- ddl-end --
-- 

-- object: public.user_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.user_id_seq CASCADE;
CREATE SEQUENCE public.user_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.user_id_seq OWNER TO postgres;
-- ddl-end --

-- object: public.user_account | type: TABLE --
-- DROP TABLE IF EXISTS public.user_account CASCADE;
CREATE TABLE public.user_account(
	id serial NOT NULL,
	name character varying(50) NOT NULL,
	email character varying(50) NOT NULL,
	password character varying(128) NOT NULL,
	created timestamp NOT NULL,
	updated timestamp NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (id),
	CONSTRAINT username_uq UNIQUE (name),
	CONSTRAINT email_uq UNIQUE (email)

);
-- ddl-end --

-- object: public.order_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.order_id_seq CASCADE;
CREATE SEQUENCE public.order_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.order_id_seq OWNER TO postgres;
-- ddl-end --

-- object: public."order" | type: TABLE --
-- DROP TABLE IF EXISTS public."order" CASCADE;
CREATE TABLE public."order"(
	id serial NOT NULL,
	created timestamp NOT NULL,
	updated timestamp NOT NULL,
	user_account_id integer NOT NULL,
	CONSTRAINT order_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public."order" OWNER TO postgres;
-- ddl-end --

-- object: public.goods_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.goods_id_seq CASCADE;
CREATE SEQUENCE public.goods_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.goods_id_seq OWNER TO postgres;
-- ddl-end --

-- object: public.license_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.license_id_seq CASCADE;
CREATE SEQUENCE public.license_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.license_id_seq OWNER TO postgres;
-- ddl-end --

-- object: public.song_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.song_id_seq CASCADE;
CREATE SEQUENCE public.song_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.song_id_seq OWNER TO postgres;
-- ddl-end --

-- object: public.book | type: TABLE --
-- DROP TABLE IF EXISTS public.book CASCADE;
CREATE TABLE public.book(
	id serial NOT NULL,
	product_id smallint NOT NULL,
	sample_file varchar(150),
	cover varchar(128),
	CONSTRAINT song_pk PRIMARY KEY (id)

);
-- ddl-end --
COMMENT ON COLUMN public.book.sample_file IS 'mp3 file';
-- ddl-end --

-- object: public.order_item_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.order_item_id_seq CASCADE;
CREATE SEQUENCE public.order_item_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.order_item_id_seq OWNER TO postgres;
-- ddl-end --

-- object: public.role | type: TABLE --
-- DROP TABLE IF EXISTS public.role CASCADE;
CREATE TABLE public.role(
	id serial NOT NULL,
	name varchar(150) NOT NULL,
	CONSTRAINT role_pk PRIMARY KEY (id),
	CONSTRAINT name_uq UNIQUE (name)

);
-- ddl-end --

-- object: public.permission | type: TABLE --
-- DROP TABLE IF EXISTS public.permission CASCADE;
CREATE TABLE public.permission(
	id serial NOT NULL,
	content_type smallint NOT NULL,
	"create" bool NOT NULL DEFAULT FALSE,
	read bool NOT NULL DEFAULT FALSE,
	update bool NOT NULL DEFAULT FALSE,
	delete bool NOT NULL DEFAULT FALSE,
	read_all bool NOT NULL DEFAULT FALSE,
	edit_all bool NOT NULL DEFAULT FALSE,
	CONSTRAINT permission_pk PRIMARY KEY (id)

);
-- ddl-end --

-- object: public.many_role_has_many_permission | type: TABLE --
-- DROP TABLE IF EXISTS public.many_role_has_many_permission CASCADE;
CREATE TABLE public.many_role_has_many_permission(
	role_id integer NOT NULL,
	role_id1 integer NOT NULL,
	CONSTRAINT many_role_has_many_permission_pk PRIMARY KEY (role_id,role_id1)

);
-- ddl-end --

-- object: role_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_role_has_many_permission DROP CONSTRAINT IF EXISTS role_fk CASCADE;
ALTER TABLE public.many_role_has_many_permission ADD CONSTRAINT role_fk FOREIGN KEY (role_id)
REFERENCES public.role (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: permission_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_role_has_many_permission DROP CONSTRAINT IF EXISTS permission_fk CASCADE;
ALTER TABLE public.many_role_has_many_permission ADD CONSTRAINT permission_fk FOREIGN KEY (role_id1)
REFERENCES public.permission (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: public.order_item | type: TABLE --
-- DROP TABLE IF EXISTS public.order_item CASCADE;
CREATE TABLE public.order_item(
	id serial NOT NULL,
	order_id integer NOT NULL,
	amount money NOT NULL,
	product_id smallint,
	delivered bool NOT NULL DEFAULT false,
	fee_charged bool NOT NULL DEFAULT false,
	CONSTRAINT order_item_pk PRIMARY KEY (id)

);
-- ddl-end --

-- object: order_fk | type: CONSTRAINT --
-- ALTER TABLE public.order_item DROP CONSTRAINT IF EXISTS order_fk CASCADE;
ALTER TABLE public.order_item ADD CONSTRAINT order_fk FOREIGN KEY (order_id)
REFERENCES public."order" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.restricted_file | type: TABLE --
-- DROP TABLE IF EXISTS public.restricted_file CASCADE;
CREATE TABLE public.restricted_file(
	id serial NOT NULL,
	book_id integer NOT NULL,
	CONSTRAINT digtal_goods_pk PRIMARY KEY (id)

);
-- ddl-end --

-- object: public.download_links | type: TABLE --
-- DROP TABLE IF EXISTS public.download_links CASCADE;
CREATE TABLE public.download_links(
	id serial NOT NULL,
	created timestamp NOT NULL,
	token varchar(64),
	valid interval HOUR  NOT NULL,
	use_count integer NOT NULL,
	restricted_file_id integer NOT NULL,
	order_item_id integer NOT NULL,
	CONSTRAINT download_pk PRIMARY KEY (id)

);
-- ddl-end --

-- object: book_fk | type: CONSTRAINT --
-- ALTER TABLE public.restricted_file DROP CONSTRAINT IF EXISTS book_fk CASCADE;
ALTER TABLE public.restricted_file ADD CONSTRAINT book_fk FOREIGN KEY (book_id)
REFERENCES public.book (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: order_item_fk | type: CONSTRAINT --
-- ALTER TABLE public.download_links DROP CONSTRAINT IF EXISTS order_item_fk CASCADE;
ALTER TABLE public.download_links ADD CONSTRAINT order_item_fk FOREIGN KEY (order_item_id)
REFERENCES public.order_item (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: restricted_file_fk | type: CONSTRAINT --
-- ALTER TABLE public.download_links DROP CONSTRAINT IF EXISTS restricted_file_fk CASCADE;
ALTER TABLE public.download_links ADD CONSTRAINT restricted_file_fk FOREIGN KEY (restricted_file_id)
REFERENCES public.restricted_file (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.paypal_account | type: TABLE --
-- DROP TABLE IF EXISTS public.paypal_account CASCADE;
CREATE TABLE public.paypal_account(
	id serial NOT NULL,
	user_account_id integer NOT NULL,
	CONSTRAINT paypal_account_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.paypal_account OWNER TO postgres;
-- ddl-end --

-- object: public.author | type: TABLE --
-- DROP TABLE IF EXISTS public.author CASCADE;
CREATE TABLE public.author(
	id serial NOT NULL,
	firstaname varchar(128) NOT NULL,
	lastname varchar(128),
	photo varchar,
	bio text,
	book_id integer,
	CONSTRAINT author_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.author OWNER TO postgres;
-- ddl-end --

-- object: book_fk | type: CONSTRAINT --
-- ALTER TABLE public.author DROP CONSTRAINT IF EXISTS book_fk CASCADE;
ALTER TABLE public.author ADD CONSTRAINT book_fk FOREIGN KEY (book_id)
REFERENCES public.book (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.language | type: TABLE --
-- DROP TABLE IF EXISTS public.language CASCADE;
CREATE TABLE public.language(
	code varchar(3) NOT NULL,
	name varchar(128) NOT NULL,
	CONSTRAINT language_pk PRIMARY KEY (code),
	CONSTRAINT language_name_uq UNIQUE (name),
	CONSTRAINT language_code_name_uq UNIQUE (code,name)

);
-- ddl-end --
COMMENT ON COLUMN public.language.code IS 'ISO 639-2';
-- ddl-end --

-- object: public.genre | type: TABLE --
-- DROP TABLE IF EXISTS public.genre CASCADE;
CREATE TABLE public.genre(
	genre_name varchar(128) NOT NULL,
	CONSTRAINT genre_pk PRIMARY KEY (genre_name)

);
-- ddl-end --

-- object: public.many_book_has_many_language | type: TABLE --
-- DROP TABLE IF EXISTS public.many_book_has_many_language CASCADE;
CREATE TABLE public.many_book_has_many_language(
	book_id integer NOT NULL,
	book_code varchar(3) NOT NULL,
	CONSTRAINT many_book_has_many_language_pk PRIMARY KEY (book_id,book_code)

);
-- ddl-end --

-- object: book_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_book_has_many_language DROP CONSTRAINT IF EXISTS book_fk CASCADE;
ALTER TABLE public.many_book_has_many_language ADD CONSTRAINT book_fk FOREIGN KEY (book_id)
REFERENCES public.book (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: language_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_book_has_many_language DROP CONSTRAINT IF EXISTS language_fk CASCADE;
ALTER TABLE public.many_book_has_many_language ADD CONSTRAINT language_fk FOREIGN KEY (book_code)
REFERENCES public.language (code) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.many_book_has_many_genre | type: TABLE --
-- DROP TABLE IF EXISTS public.many_book_has_many_genre CASCADE;
CREATE TABLE public.many_book_has_many_genre(
	book_id integer NOT NULL,
	book_genre_name varchar(128) NOT NULL,
	CONSTRAINT many_book_has_many_genre_pk PRIMARY KEY (book_id,book_genre_name)

);
-- ddl-end --

-- object: book_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_book_has_many_genre DROP CONSTRAINT IF EXISTS book_fk CASCADE;
ALTER TABLE public.many_book_has_many_genre ADD CONSTRAINT book_fk FOREIGN KEY (book_id)
REFERENCES public.book (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: genre_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_book_has_many_genre DROP CONSTRAINT IF EXISTS genre_fk CASCADE;
ALTER TABLE public.many_book_has_many_genre ADD CONSTRAINT genre_fk FOREIGN KEY (book_genre_name)
REFERENCES public.genre (genre_name) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: user_account_fk | type: CONSTRAINT --
-- ALTER TABLE public."order" DROP CONSTRAINT IF EXISTS user_account_fk CASCADE;
ALTER TABLE public."order" ADD CONSTRAINT user_account_fk FOREIGN KEY (user_account_id)
REFERENCES public.user_account (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: user_account_fk | type: CONSTRAINT --
-- ALTER TABLE public.paypal_account DROP CONSTRAINT IF EXISTS user_account_fk CASCADE;
ALTER TABLE public.paypal_account ADD CONSTRAINT user_account_fk FOREIGN KEY (user_account_id)
REFERENCES public.user_account (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: paypal_account_uq | type: CONSTRAINT --
-- ALTER TABLE public.paypal_account DROP CONSTRAINT IF EXISTS paypal_account_uq CASCADE;
ALTER TABLE public.paypal_account ADD CONSTRAINT paypal_account_uq UNIQUE (user_account_id);
-- ddl-end --

-- object: public.product | type: TABLE --
-- DROP TABLE IF EXISTS public.product CASCADE;
CREATE TABLE public.product(
	id smallint NOT NULL,
	type smallint NOT NULL,
	user_account_id integer NOT NULL,
	price money NOT NULL,
	CONSTRAINT product_pk PRIMARY KEY (id)

);
-- ddl-end --

-- object: product_fk | type: CONSTRAINT --
-- ALTER TABLE public.book DROP CONSTRAINT IF EXISTS product_fk CASCADE;
ALTER TABLE public.book ADD CONSTRAINT product_fk FOREIGN KEY (product_id)
REFERENCES public.product (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: book_uq | type: CONSTRAINT --
-- ALTER TABLE public.book DROP CONSTRAINT IF EXISTS book_uq CASCADE;
ALTER TABLE public.book ADD CONSTRAINT book_uq UNIQUE (product_id);
-- ddl-end --

-- object: product_fk | type: CONSTRAINT --
-- ALTER TABLE public.order_item DROP CONSTRAINT IF EXISTS product_fk CASCADE;
ALTER TABLE public.order_item ADD CONSTRAINT product_fk FOREIGN KEY (product_id)
REFERENCES public.product (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: user_account_fk | type: CONSTRAINT --
-- ALTER TABLE public.product DROP CONSTRAINT IF EXISTS user_account_fk CASCADE;
ALTER TABLE public.product ADD CONSTRAINT user_account_fk FOREIGN KEY (user_account_id)
REFERENCES public.user_account (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.payment | type: TABLE --
-- DROP TABLE IF EXISTS public.payment CASCADE;
CREATE TABLE public.payment(
	id serial NOT NULL,
	payload text NOT NULL,
	created timestamp,
	order_id integer NOT NULL,
	CONSTRAINT payment_pk PRIMARY KEY (id)

);
-- ddl-end --

-- object: order_fk | type: CONSTRAINT --
-- ALTER TABLE public.payment DROP CONSTRAINT IF EXISTS order_fk CASCADE;
ALTER TABLE public.payment ADD CONSTRAINT order_fk FOREIGN KEY (order_id)
REFERENCES public."order" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.many_user_account_has_many_role | type: TABLE --
-- DROP TABLE IF EXISTS public.many_user_account_has_many_role CASCADE;
CREATE TABLE public.many_user_account_has_many_role(
	user_account_id integer NOT NULL,
	user_account_id1 integer NOT NULL,
	CONSTRAINT many_user_account_has_many_role_pk PRIMARY KEY (user_account_id,user_account_id1)

);
-- ddl-end --

-- object: user_account_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_user_account_has_many_role DROP CONSTRAINT IF EXISTS user_account_fk CASCADE;
ALTER TABLE public.many_user_account_has_many_role ADD CONSTRAINT user_account_fk FOREIGN KEY (user_account_id)
REFERENCES public.user_account (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: role_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_user_account_has_many_role DROP CONSTRAINT IF EXISTS role_fk CASCADE;
ALTER TABLE public.many_user_account_has_many_role ADD CONSTRAINT role_fk FOREIGN KEY (user_account_id1)
REFERENCES public.role (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.review | type: TABLE --
-- DROP TABLE IF EXISTS public.review CASCADE;
CREATE TABLE public.review(
	id serial NOT NULL,
	grade smallint NOT NULL,
	comment text,
	product_id smallint NOT NULL,
	order_id integer NOT NULL,
	CONSTRAINT reviews_pk PRIMARY KEY (id)

);
-- ddl-end --

-- object: product_fk | type: CONSTRAINT --
-- ALTER TABLE public.review DROP CONSTRAINT IF EXISTS product_fk CASCADE;
ALTER TABLE public.review ADD CONSTRAINT product_fk FOREIGN KEY (product_id)
REFERENCES public.product (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: order_fk | type: CONSTRAINT --
-- ALTER TABLE public.review DROP CONSTRAINT IF EXISTS order_fk CASCADE;
ALTER TABLE public.review ADD CONSTRAINT order_fk FOREIGN KEY (order_id)
REFERENCES public."order" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: review_uq | type: CONSTRAINT --
-- ALTER TABLE public.review DROP CONSTRAINT IF EXISTS review_uq CASCADE;
ALTER TABLE public.review ADD CONSTRAINT review_uq UNIQUE (order_id);
-- ddl-end --

-- object: public."like" | type: TABLE --
-- DROP TABLE IF EXISTS public."like" CASCADE;
CREATE TABLE public."like"(
	id serial NOT NULL,
	created timestamp NOT NULL,
	user_account_id integer NOT NULL,
	product_id smallint NOT NULL,
	CONSTRAINT like_pk PRIMARY KEY (id)

);
-- ddl-end --

-- object: user_account_fk | type: CONSTRAINT --
-- ALTER TABLE public."like" DROP CONSTRAINT IF EXISTS user_account_fk CASCADE;
ALTER TABLE public."like" ADD CONSTRAINT user_account_fk FOREIGN KEY (user_account_id)
REFERENCES public.user_account (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: product_fk | type: CONSTRAINT --
-- ALTER TABLE public."like" DROP CONSTRAINT IF EXISTS product_fk CASCADE;
ALTER TABLE public."like" ADD CONSTRAINT product_fk FOREIGN KEY (product_id)
REFERENCES public.product (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


