-- CARS
CREATE TABLE "model" (
  "id" serial NOT NULL,
  CONSTRAINT "model_pk" PRIMARY KEY ("id")
);

CREATE TABLE "model_info" (
  "id" integer NOT NULL,
  CONSTRAINT "model_info_pk" PRIMARY KEY ("id")
);

ALTER TABLE "model_info" ADD CONSTRAINT "model_info_fk0" FOREIGN KEY ("id")
REFERENCES "model"("id");

-- MARKETPLACE
CREATE TABLE "product"(
	"id" serial NOT NULL,
	CONSTRAINT "product_pk" PRIMARY KEY ("id")
);

CREATE TABLE "book"(
	"id" integer NOT NULL,
	CONSTRAINT "book_pk" PRIMARY KEY ("id")
);

ALTER TABLE "book" ADD CONSTRAINT "product_fk" FOREIGN KEY ("id")
REFERENCES "product" ("id");
