
CREATE TABLE "ACCOUNT" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"NCPT" VARCHAR2(255 CHAR), 
	 PRIMARY KEY ("ID")
  ) ;

 
-- CREATE UNIQUE INDEX "SYS_C00255245" ON "ACCOUNT" ("ID") 
  ;
;

CREATE TABLE "APP_PROFILE" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"code" VARCHAR2(255 CHAR), 
	"Enabled" NUMBER(1,0) NOT NULL ENABLE, 
	"nom" VARCHAR2(255 CHAR), 
	 PRIMARY KEY ("id")
  ) ;

-- CREATE UNIQUE INDEX "SYS_C00255248" ON "APP_PROFILE" ("id") 
   ;
;

CREATE TABLE "APP_PROFILES_ROLES" 
   (	"idProfile" NUMBER(19,0) NOT NULL ENABLE, 
	"idRole" NUMBER(19,0) NOT NULL ENABLE
   )  ;

ALTER TABLE "APP_PROFILES_ROLES" ADD CONSTRAINT "FK47kmt7kwyos1q47gqs2auq8wn" FOREIGN KEY ("idProfile")
	  REFERENCES "APP_PROFILE" ("id") ENABLE;

CREATE TABLE "APP_ROLE" 
   (	"Role_Id" NUMBER(19,0) NOT NULL ENABLE, 
	"action" VARCHAR2(255 CHAR), 
	"controller" VARCHAR2(255 CHAR), 
	"module" VARCHAR2(255 CHAR), 
	"Role_Name" VARCHAR2(30 CHAR) NOT NULL ENABLE, 
	 PRIMARY KEY ("Role_Id")
  ) ;

   ALTER TABLE "APP_PROFILES_ROLES" ADD CONSTRAINT "FKjmnfo3n8cy2rcqdoc9eepv2vn" FOREIGN KEY ("idRole")
	  REFERENCES "APP_ROLE" ("Role_Id") ENABLE;

 CREATE UNIQUE INDEX "APP_ROLE_UK" ON "APP_ROLE" ("Role_Name") 
  ;
 
 -- CREATE UNIQUE INDEX "SYS_C00255253" ON "APP_ROLE" ("Role_Id") 
   ;
;

CREATE TABLE "APP_USER" 
   (	"USER_ID" NUMBER(19,0) NOT NULL ENABLE, 
	"ENABLED" NUMBER(1,0) NOT NULL ENABLE, 
	"ENCRYTED_PASSWORD" VARCHAR2(128 CHAR) NOT NULL ENABLE, 
	"NOM" VARCHAR2(255 CHAR), 
	"PRENOM" VARCHAR2(255 CHAR), 
	"USER_NAME" VARCHAR2(36 CHAR) NOT NULL ENABLE, 
	"ID_PROFILE" NUMBER(19,0), 
	 PRIMARY KEY ("USER_ID")
  ) ;

ALTER TABLE "APP_USER" ADD CONSTRAINT "FKcoaysrgvl1g9v0sioelyxpogd" FOREIGN KEY ("ID_PROFILE")
	  REFERENCES "APP_PROFILE" ("id") ENABLE;

CREATE UNIQUE INDEX "APP_USER_UK" ON "APP_USER" ("USER_NAME") 
   ;
 
--  CREATE UNIQUE INDEX "SYS_C00255258" ON "APP_USER" ("USER_ID") 
   ;
;

CREATE TABLE "App_USER_FLUX" 
   (	"ID_USER" NUMBER(19,0) NOT NULL ENABLE, 
	"ID_FLUX" NUMBER(19,0) NOT NULL ENABLE, 
	 PRIMARY KEY ("ID_USER", "ID_FLUX")
  ) ;

 CREATE TABLE "PARAMETRAGE_FLUX" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"code" VARCHAR2(255 CHAR), 
	"nom" VARCHAR2(255 CHAR), 
	 PRIMARY KEY ("id")
) ;

ALTER TABLE "App_USER_FLUX" ADD CONSTRAINT "FKcxl6u1spb3xphioh4ci5i2nw0" FOREIGN KEY ("ID_USER")
	  REFERENCES "APP_USER" ("USER_ID") ENABLE;
 
  ALTER TABLE "App_USER_FLUX" ADD CONSTRAINT "FKrmragrbsf8hp9clr4ql3ct3w4" FOREIGN KEY ("ID_FLUX")
	  REFERENCES "PARAMETRAGE_FLUX" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255261" ON "App_USER_FLUX" ("ID_USER", "ID_FLUX") 
   ;

CREATE TABLE "CHARGEMENT" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"DATE_CHARGEMENT" TIMESTAMP (6), 
	"DATE_FIN_CHARGEMENT" TIMESTAMP (6), 
	"FIRST_MAGIC" NUMBER(19,0), 
	"LAST_MAGIC" NUMBER(19,0), 
	"STATUS" NUMBER(10,0), 
	"FLUX_ID" NUMBER(19,0), 
	 PRIMARY KEY ("id")
 ) ;

ALTER TABLE "CHARGEMENT" ADD CONSTRAINT "FKc92v2pxvrncdmendackd0mwnu" FOREIGN KEY ("FLUX_ID")
	  REFERENCES "PARAMETRAGE_FLUX" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255563" ON "CHARGEMENT" ("id") 
   ;
;

CREATE TABLE "CS_ME_CDCOH" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"COMPID" VARCHAR2(255 CHAR), 
	"CREDATE" DATE, 
	"CURRCODE" VARCHAR2(255 CHAR), 
	"EXCHRATE" FLOAT(126), 
	"IMCOMMNO" VARCHAR2(255 CHAR), 
	"IMFILENO" VARCHAR2(255 CHAR), 
	"INVALUE" FLOAT(126), 
	"INVDATE" DATE, 
	"INVNO" NUMBER(19,0), 
	"SHIPDATE" DATE, 
	"SHIPNAME" VARCHAR2(255 CHAR), 
	"SHIPNO" VARCHAR2(255 CHAR), 
	"SUPACC" VARCHAR2(255 CHAR), 
	"SUPPLIER" VARCHAR2(255 CHAR), 
	"USERID" VARCHAR2(255 CHAR), 
	"VATCODE" VARCHAR2(255 CHAR), 
	"VATVALUE" FLOAT(126), 
	"DATE_CHARGEMENT" TIMESTAMP (6), 
	"DATE_ENVOI" TIMESTAMP (6), 
	"FLAG_CHARGE" NUMBER(1,0), 
	"MAGIC" NUMBER(19,0), 
	"FLAG_ENVOI" NUMBER(1,0), 
	"ID_CHARGEMENT" NUMBER(19,0), 
	 PRIMARY KEY ("ID")
  ) ;

ALTER TABLE "CS_ME_CDCOH" ADD CONSTRAINT "FKeoc0rb6fkhidj8vj9dx40buyi" FOREIGN KEY ("ID_CHARGEMENT")
	  REFERENCES "CHARGEMENT" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255541" ON "CS_ME_CDCOH" ("ID") 
  ;
;

CREATE TABLE "CS_ME_CDCOL" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"ANALDESC" VARCHAR2(255 CHAR), 
	"COMM" VARCHAR2(255 CHAR), 
	"COMPID" VARCHAR2(255 CHAR), 
	"FRANDESC" VARCHAR2(255 CHAR), 
	"INVALUE" FLOAT(126), 
	"LOCN" VARCHAR2(255 CHAR), 
	"PRODDESC" VARCHAR2(255 CHAR), 
	"PRODUCT" VARCHAR2(255 CHAR), 
	"PROGRESS" VARCHAR2(255 CHAR), 
	"STATUS" VARCHAR2(255 CHAR), 
	"VATCODE" VARCHAR2(255 CHAR), 
	"VATVALUE" FLOAT(126), 
	"ANALYSIS" VARCHAR2(255 CHAR), 
	"CHASSIS" VARCHAR2(255 CHAR), 
	"FRAN" VARCHAR2(255 CHAR), 
	"MAGIC" NUMBER(19,0), 
	"VEHICLE" NUMBER(19,0), 
	"HEADER_ID" NUMBER(19,0), 
	 PRIMARY KEY ("ID")
  ) ;

ALTER TABLE "CS_ME_CDCOL" ADD CONSTRAINT "FKm7rw3vwbnos0yxmpalj3t68c1" FOREIGN KEY ("HEADER_ID")
	  REFERENCES "CS_ME_CDCOH" ("ID") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255543" ON "CS_ME_CDCOL" ("ID") 
  ;
;

CREATE TABLE "CS_ME_CDDEL" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"CHASSIS" VARCHAR2(255 CHAR), 
	"COMPID" VARCHAR2(255 CHAR), 
	"CREDATE" DATE, 
	"CREDAGNO" VARCHAR2(100), 
	"CRNDATE" DATE, 
	"CRNNO" NUMBER(19,0), 
	"CUSACC" VARCHAR2(255 CHAR), 
	"CUSDATE" DATE, 
	"CUSNAME" VARCHAR2(255 CHAR), 
	"CUSTCODE" NUMBER(19,0), 
	"CUSTNAME" VARCHAR2(255 CHAR), 
	"CUSTORD" VARCHAR2(20), 
	"DELDATE" DATE, 
	"DELNOTE" VARCHAR2(100), 
	"FRAN" VARCHAR2(255 CHAR), 
	"FRANDESC" VARCHAR2(255 CHAR), 
	"INVACC" VARCHAR2(255 CHAR), 
	"INVDATE" DATE, 
	"INVNAME" VARCHAR2(255 CHAR), 
	"INVOICE" NUMBER(19,0), 
	"INVALUE" NUMBER(19,2), 
	"MODDESC" VARCHAR2(255 CHAR), 
	"MODEL" VARCHAR2(255 CHAR), 
	"OPERTYPE" VARCHAR2(255 CHAR), 
	"PROGRESS" VARCHAR2(255 CHAR), 
	"RECPRI1" NUMBER(19,2), 
	"RECPRI10" NUMBER(19,2), 
	"RECPRI11" NUMBER(19,2), 
	"RECPRI12" NUMBER(19,2), 
	"RECPRI13" NUMBER(19,2), 
	"RECPRI14" NUMBER(19,2), 
	"RECPRI15" NUMBER(19,2), 
	"RECPRI2" NUMBER(19,2), 
	"RECPRI3" NUMBER(19,2), 
	"RECPRI4" NUMBER(19,2), 
	"RECPRI5" NUMBER(19,2), 
	"RECPRI6" NUMBER(19,2), 
	"RECPRI7" NUMBER(19,2), 
	"RECPRI8" NUMBER(19,2), 
	"RECPRI9" NUMBER(19,2), 
	"REGN" VARCHAR2(100), 
	"SALENET" FLOAT(126), 
	"SALEVAT" NUMBER(19,2), 
	"SELLLOCN" VARCHAR2(255 CHAR), 
	"STATUS" VARCHAR2(255 CHAR), 
	"STYPE" VARCHAR2(255 CHAR), 
	"TOTCOST" FLOAT(126), 
	"TOTNRCOST" VARCHAR2(255 CHAR), 
	"USERID" VARCHAR2(100), 
	"VARDESC" VARCHAR2(255 CHAR), 
	"VARIANT" VARCHAR2(255 CHAR), 
	"VATEXDAT" DATE, 
	"VATEXNO" VARCHAR2(100), 
	"VEHCOST" NUMBER(19,2), 
	"VEHICLE" NUMBER(19,0), 
	"VEHTAX" VARCHAR2(255 CHAR), 
	"DATE_CHARGEMENT" TIMESTAMP (6), 
	"DATE_ENVOI" TIMESTAMP (6), 
	"LOADED" NUMBER(1,0), 
	"MAGIC" NUMBER(19,0), 
	"SENT" NUMBER(1,0), 
	"ID_CHARGEMENT" NUMBER(19,0), 
	"LUXTAX" NUMBER(19,2), 
	"TOTNRCST" NUMBER(19,2), 
	 PRIMARY KEY ("ID")
  );

-- CREATE UNIQUE INDEX "SYS_C00255577" ON "CS_ME_CDDEL" ("ID") 
  ;
;

CREATE TABLE "CS_ME_CDDEP" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"ACCNAME" VARCHAR2(255 CHAR), 
	"ACCOUNT" VARCHAR2(255 CHAR), 
	"CHASSIS" VARCHAR2(255 CHAR), 
	"CREDATE" DATE, 
	"CUSTCODE" NUMBER(19,0), 
	"CUSTNAME" VARCHAR2(255 CHAR), 
	"DEPREF" VARCHAR2(255 CHAR), 
	"DEPVALUE" FLOAT(126), 
	"DOCDATE" DATE, 
	"DOCNUM" NUMBER(19,0), 
	"FRAN" VARCHAR2(255 CHAR), 
	"FRANDESC" VARCHAR2(255 CHAR), 
	"LNKCOMP" VARCHAR2(255 CHAR), 
	"MODDESC" VARCHAR2(255 CHAR), 
	"MODEL" VARCHAR2(255 CHAR), 
	"ORDDATE" DATE, 
	"ORDNUM" NUMBER(19,0), 
	"PAYMCODE" VARCHAR2(255 CHAR), 
	"SELLPRIC" FLOAT(126), 
	"STAMPDUT" NUMBER(19,2), 
	"USERID" VARCHAR2(255), 
	"VARDESC" VARCHAR2(255 CHAR), 
	"VARIANT" VARCHAR2(255 CHAR), 
	"VATCODE" VARCHAR2(255 CHAR), 
	"VATVALUE" FLOAT(126), 
	"VEHICLE" NUMBER(19,0), 
	"VSCOMPID" VARCHAR2(255 CHAR), 
	"DATE_CHARGEMENT" TIMESTAMP (6), 
	"DATE_ENVOI" TIMESTAMP (6), 
	"FLAG_CHARGE" NUMBER(1,0), 
	"MAGIC" NUMBER(19,0), 
	"FLAG_ENVOI" NUMBER(1,0), 
	"ID_CHARGEMENT" NUMBER(19,0), 
	"SEQUENCE" NUMBER(19,0), 
	 PRIMARY KEY ("ID")
 ) ;

ALTER TABLE "CS_ME_CDDEP" ADD CONSTRAINT "FK28537y39xelo06ev9xamo0cep" FOREIGN KEY ("ID_CHARGEMENT")
	  REFERENCES "CHARGEMENT" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255547" ON "CS_ME_CDDEP" ("ID") 
 ;
;

CREATE TABLE "CS_ME_CDFIH" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"COMPID" VARCHAR2(255 CHAR), 
	"CREDATE" DATE, 
	"FRAN" VARCHAR2(255 CHAR), 
	"FRANDESC" VARCHAR2(255 CHAR), 
	"IMCOMMNO" VARCHAR2(255 CHAR), 
	"IMFILENO" VARCHAR2(255 CHAR), 
	"PINVDATE" TIMESTAMP (6), 
	"PINVNO" VARCHAR2(255 CHAR), 
	"SHIPDATE" TIMESTAMP (6), 
	"SHIPNAME" VARCHAR2(255 CHAR), 
	"SHIPNO" VARCHAR2(255 CHAR), 
	"SUPACC" VARCHAR2(255 CHAR), 
	"SUPPLIER" VARCHAR2(255 CHAR), 
	"USERID" VARCHAR2(255 CHAR), 
	"DATE_CHARGEMENT" TIMESTAMP (6), 
	"DATE_ENVOI" TIMESTAMP (6), 
	"LOADED" NUMBER(1,0), 
	"MAGIC" NUMBER(19,0), 
	"SENT" NUMBER(1,0), 
	"ID_CHARGEMENT" NUMBER(19,0), 
	 PRIMARY KEY ("ID")
) ;

ALTER TABLE "CS_ME_CDFIH" ADD CONSTRAINT "FK6e9u7oyximlxkbpd0aghdja95" FOREIGN KEY ("ID_CHARGEMENT")
	  REFERENCES "CHARGEMENT" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255549" ON "CS_ME_CDFIH" ("ID") 
 ;
;

CREATE TABLE "CS_ME_CDFIL" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"BUYCURR" VARCHAR2(255 CHAR), 
	"BUYEXCH" FLOAT(126), 
	"COLCODE" VARCHAR2(255 CHAR), 
	"COLDESC" VARCHAR2(255 CHAR), 
	"COMM" VARCHAR2(255 CHAR), 
	"CONS" FLOAT(126), 
	"FINDATE" DATE, 
	"FRAN" VARCHAR2(255 CHAR), 
	"FRANDESC" VARCHAR2(255 CHAR), 
	"FREIGHT" FLOAT(126), 
	"MODDESC" VARCHAR2(255 CHAR), 
	"MODEL" VARCHAR2(255 CHAR), 
	"TRIMCODE" VARCHAR2(255 CHAR), 
	"TRIMDESC" VARCHAR2(255 CHAR), 
	"VARDESC" VARCHAR2(255 CHAR), 
	"VARIANT" VARCHAR2(255 CHAR), 
	"CHASSIS" VARCHAR2(255 CHAR), 
	"COMPID" VARCHAR2(255 CHAR), 
	"MAGIC" NUMBER(19,0), 
	"VEHICLE" FLOAT(126), 
	"HEADER_ID" NUMBER(19,0), 
	 PRIMARY KEY ("ID")
);

ALTER TABLE "CS_ME_CDFIL" ADD CONSTRAINT "FK9j2chks0mpo0jh9xi3gt6jbum" FOREIGN KEY ("HEADER_ID")
	  REFERENCES "CS_ME_CDFIH" ("ID") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255551" ON "CS_ME_CDFIL" ("ID") 
 ;
;

CREATE TABLE "CS_ME_CDINV" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"CHASSIS" VARCHAR2(255 CHAR), 
	"COMPID" VARCHAR2(255 CHAR), 
	"CREDAGNO" VARCHAR2(255), 
	"CREDATE" DATE, 
	"CRNDATE" DATE, 
	"CRNNO" NUMBER(19,0), 
	"CUSACC" VARCHAR2(255 CHAR), 
	"CUSDATE" DATE, 
	"CUSNAME" VARCHAR2(255 CHAR), 
	"CUSTCODE" VARCHAR2(255 CHAR), 
	"CUSTNAME" VARCHAR2(255 CHAR), 
	"CUSTORD" VARCHAR2(20), 
	"DELDATE" DATE, 
	"DELNOTE" VARCHAR2(255), 
	"FRAN" VARCHAR2(255 CHAR), 
	"FRANDESC" VARCHAR2(255 CHAR), 
	"INVACC" VARCHAR2(255 CHAR), 
	"INVALUE" FLOAT(126), 
	"INVDATE" DATE, 
	"INVNAME" VARCHAR2(255 CHAR), 
	"INVOICE" NUMBER(19,0), 
	"MODDESC" VARCHAR2(255 CHAR), 
	"MODEL" VARCHAR2(255 CHAR), 
	"OPERTYPE" VARCHAR2(255 CHAR), 
	"PROGRESS" VARCHAR2(255 CHAR), 
	"RECPRI1" FLOAT(126), 
	"RECPRI10" FLOAT(126), 
	"RECPRI11" FLOAT(126), 
	"RECPRI12" FLOAT(126), 
	"RECPRI13" FLOAT(126), 
	"RECPRI14" FLOAT(126), 
	"RECPRI15" FLOAT(126), 
	"RECPRI2" FLOAT(126), 
	"RECPRI3" FLOAT(126), 
	"RECPRI4" FLOAT(126), 
	"RECPRI5" FLOAT(126), 
	"RECPRI6" FLOAT(126), 
	"RECPRI7" FLOAT(126), 
	"RECPRI8" FLOAT(126), 
	"RECPRI9" FLOAT(126), 
	"REGN" VARCHAR2(30), 
	"SALENET" FLOAT(126), 
	"SALEVAT" FLOAT(126), 
	"SELLLOCN" VARCHAR2(255 CHAR), 
	"STATUS" VARCHAR2(255 CHAR), 
	"STYPE" VARCHAR2(255 CHAR), 
	"TOTCOST" FLOAT(126), 
	"USERID" VARCHAR2(255 CHAR), 
	"VARDESC" VARCHAR2(255 CHAR), 
	"VARIANT" VARCHAR2(255 CHAR), 
	"VATEXDAT" DATE, 
	"VATEXNO" VARCHAR2(255), 
	"VEHCOST" FLOAT(126), 
	"VEHICLE" NUMBER(19,0), 
	"LUXTAX" FLOAT(126), 
	"DATE_CHARGEMENT" TIMESTAMP (6), 
	"DATE_ENVOI" TIMESTAMP (6), 
	"FLAG_CHARGE" NUMBER(1,0), 
	"MAGIC" NUMBER(19,0), 
	"FLAG_ENVOI" NUMBER(1,0), 
	"ID_CHARGEMENT" NUMBER(19,0), 
	"TOTNRCST" NUMBER(19,2), 
	 PRIMARY KEY ("ID")
) ;

ALTER TABLE "CS_ME_CDINV" ADD CONSTRAINT "FK3vu9vo8p0cr8hkbvuryes4rd5" FOREIGN KEY ("ID_CHARGEMENT")
	  REFERENCES "CHARGEMENT" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255553" ON "CS_ME_CDINV" ("ID") 
;
;

CREATE TABLE "CS_ME_CDMOV" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"LOCNNEW" VARCHAR2(255 CHAR), 
	"BUYLOC" VARCHAR2(255 CHAR), 
	"CHASSIS" VARCHAR2(255 CHAR), 
	"COMM" VARCHAR2(255 CHAR), 
	"COMPID" VARCHAR2(255 CHAR), 
	"COST" FLOAT(126), 
	"CREDATE" DATE, 
	"FRAN" VARCHAR2(255 CHAR), 
	"FRANDESC" VARCHAR2(255 CHAR), 
	"LOCN" VARCHAR2(255 CHAR), 
	"MODDESC" VARCHAR2(255 CHAR), 
	"MODEL" VARCHAR2(255 CHAR), 
	"OPERTYPE" VARCHAR2(255 CHAR), 
	"PHYLOC" VARCHAR2(255 CHAR), 
	"PROGRESS" VARCHAR2(255 CHAR), 
	"STATUS" VARCHAR2(255 CHAR), 
	"USERID" VARCHAR2(255 CHAR), 
	"VARDESC" VARCHAR2(255 CHAR), 
	"VARIANT" VARCHAR2(255 CHAR), 
	"VEHICLE" NUMBER(19,0), 
	"DATE_CHARGEMENT" TIMESTAMP (6), 
	"DATE_ENVOI" TIMESTAMP (6), 
	"LOADED" NUMBER(1,0), 
	"MAGIC" NUMBER(19,0), 
	"SENT" NUMBER(1,0), 
	"ID_CHARGEMENT" NUMBER(19,0), 
	 PRIMARY KEY ("ID")
) ;

ALTER TABLE "CS_ME_CDMOV" ADD CONSTRAINT "FK5vp5dajojt46xf57eiamcdkg9" FOREIGN KEY ("ID_CHARGEMENT")
	  REFERENCES "CHARGEMENT" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255555" ON "CS_ME_CDMOV" ("ID") 
;
;

CREATE TABLE "CS_ME_CDORD" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"BUYCURR" VARCHAR2(255 CHAR), 
	"BUYEXCH" FLOAT(126), 
	"COLCODE" VARCHAR2(255 CHAR), 
	"COLDESC" VARCHAR2(255 CHAR), 
	"COMM" VARCHAR2(255 CHAR), 
	"COMPID" NUMBER(19,0), 
	"CONS" FLOAT(126), 
	"CREDATE" DATE, 
	"FINDATE" DATE, 
	"FRAN" VARCHAR2(255 CHAR), 
	"FRANDESC" VARCHAR2(255 CHAR), 
	"FREIGHT" FLOAT(126), 
	"MODDESC" VARCHAR2(255 CHAR), 
	"MODEL" VARCHAR2(255 CHAR), 
	"ORDATE" DATE, 
	"ORDDESC" VARCHAR2(255 CHAR), 
	"ORDER" VARCHAR2(255 CHAR), 
	"ORDSTAT" VARCHAR2(255 CHAR), 
	"ORDTYPE" VARCHAR2(255 CHAR), 
	"RECDATEE" DATE, 
	"STATUS" VARCHAR2(255 CHAR), 
	"SUPACC" VARCHAR2(255 CHAR), 
	"SUPPLIER" VARCHAR2(255 CHAR), 
	"TRIMCODE" VARCHAR2(255 CHAR), 
	"TRIMDESC" VARCHAR2(255 CHAR), 
	"VARDESC" VARCHAR2(255 CHAR), 
	"VARIANT" VARCHAR2(255 CHAR), 
	"VEHICLE" VARCHAR2(255 CHAR), 
	"DATE_CHARGEMENT" TIMESTAMP (6), 
	"DATE_ENVOI" TIMESTAMP (6), 
	"FLAG_CHARGE" NUMBER(1,0), 
	"MAGIC" NUMBER(19,0), 
	"FLAG_ENVOI" NUMBER(1,0), 
	"ID_CHARGEMENT" NUMBER(19,0), 
	 PRIMARY KEY ("ID")
 ) ;

ALTER TABLE "CS_ME_CDORD" ADD CONSTRAINT "FK9lr5s8x46mci8ixirjtc5xqs7" FOREIGN KEY ("ID_CHARGEMENT")
	  REFERENCES "CHARGEMENT" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255557" ON "CS_ME_CDORD" ("ID") 
 ;
;

CREATE TABLE "CS_ME_CDPOH" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"ACCOUNT" VARCHAR2(255 CHAR), 
	"BRANCHNO" VARCHAR2(255 CHAR), 
	"CREDATE" DATE, 
	"CUSTNAME" VARCHAR2(255 CHAR), 
	"INVALUE" FLOAT(126), 
	"INVDATE" DATE, 
	"INVNO" NUMBER(19,0), 
	"USERID" VARCHAR2(255 CHAR), 
	"VATCODE" VARCHAR2(255 CHAR), 
	"VATVALUE" FLOAT(126), 
	"VSCOMPID" VARCHAR2(255 CHAR), 
	"WIPNO" NUMBER(19,0), 
	"DATE_CHARGEMENT" TIMESTAMP (6), 
	"DATE_ENVOI" TIMESTAMP (6), 
	"FLAG_CHARGE" NUMBER(1,0), 
	"MAGIC" NUMBER(19,0), 
	"FLAG_ENVOI" NUMBER(1,0), 
	"ID_CHARGEMENT" NUMBER(19,0), 
	 PRIMARY KEY ("ID")
 ) ;

ALTER TABLE "CS_ME_CDPOH" ADD CONSTRAINT "FKe9eq9g6kis5wrr28jfpkvlkok" FOREIGN KEY ("ID_CHARGEMENT")
	  REFERENCES "CHARGEMENT" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255559" ON "CS_ME_CDPOH" ("ID") 
  ;
;

CREATE TABLE "CS_ME_CDPOL" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"ANALDESC" VARCHAR2(255 CHAR), 
	"COMM" VARCHAR2(255 CHAR), 
	"FRANDESC" VARCHAR2(255 CHAR), 
	"INVALUE" FLOAT(126), 
	"LOCN" VARCHAR2(255 CHAR), 
	"PRODDESC" VARCHAR2(255 CHAR), 
	"PRODUCT" VARCHAR2(255 CHAR), 
	"PROGRESS" VARCHAR2(255 CHAR), 
	"STATUS" VARCHAR2(255 CHAR), 
	"VATCODE" VARCHAR2(255 CHAR), 
	"VATVALUE" FLOAT(126), 
	"VSCOMPID" VARCHAR2(255 CHAR), 
	"ANALYSIS" VARCHAR2(255 CHAR), 
	"CHASSIS" VARCHAR2(255 CHAR), 
	"FRAN" VARCHAR2(255 CHAR), 
	"MAGIC" NUMBER(19,0), 
	"VEHICLE" NUMBER(19,0), 
	"HEADER_ID" NUMBER(19,0), 
	 PRIMARY KEY ("ID")
 ) ;

ALTER TABLE "CS_ME_CDPOL" ADD CONSTRAINT "FKpyppbiva29p8fodwddi0cb7lf" FOREIGN KEY ("HEADER_ID")
	  REFERENCES "CS_ME_CDPOH" ("ID") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255561" ON "CS_ME_CDPOL" ("ID") 
   ;
CREATE TABLE "PIECE_COMPTABLE" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"codeJournale" VARCHAR2(255 CHAR), 
	"date" DATE, 
	"ENTETE_DOC" VARCHAR2(255 CHAR), 
	"numeroPiece" VARCHAR2(255 CHAR), 
	"SENT" NUMBER(1,0), 
	"FLUX_ID" NUMBER(19,0), 
	"SIMULATION_ID" NUMBER(19,0), 
	 PRIMARY KEY ("id")
  );
 
CREATE TABLE "ECRITURE_COMPTABLE" 
   (	"ID_ECRITURE" NUMBER(19,0) NOT NULL ENABLE, 
	"ACCOUNT_DESCRIPTION" VARCHAR2(255 CHAR), 
	"COST_CENTER" VARCHAR2(255 CHAR), 
	"MONTANT" FLOAT(126), 
	"MONTANT_MAD" FLOAT(126), 
	"NCPT" VARCHAR2(255 CHAR), 
	"REF_1" VARCHAR2(255 CHAR), 
	"REF_2" VARCHAR2(255 CHAR), 
	"REF_3" VARCHAR2(255 CHAR), 
	"REF_4" VARCHAR2(255 CHAR), 
	"REF_5" VARCHAR2(255 CHAR), 
	"REF_6" VARCHAR2(255 CHAR), 
	"SENS" CHAR(1 CHAR), 
	"PIECE_ID" NUMBER(19,0), 
	"REF_7" VARCHAR2(255 CHAR), 
	"REF_8" VARCHAR2(255 CHAR), 
	 PRIMARY KEY ("ID_ECRITURE")
) ;

ALTER TABLE "ECRITURE_COMPTABLE" ADD CONSTRAINT "FKg7xs6k3u2ca6pfojpl90ru2dg" FOREIGN KEY ("PIECE_ID")
	  REFERENCES "PIECE_COMPTABLE" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255287" ON "ECRITURE_COMPTABLE" ("ID_ECRITURE") 
  ;
;

CREATE TABLE "LISTE_ERREUR" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"description" VARCHAR2(255 CHAR), 
	"magic" NUMBER(19,0), 
	"numeroLigne" NUMBER(19,0), 
	"CHARGEMENT_ID" NUMBER(19,0), 
	"SIMULATION_ID" NUMBER(19,0), 
	 PRIMARY KEY ("id")
	 )
;

CREATE TABLE "SIMULATION" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"dateArreter" DATE, 
	"dateGeneration" TIMESTAMP (6), 
	"dateSimuation" TIMESTAMP (6), 
	"state" NUMBER(10,0), 
	"username" VARCHAR2(255 CHAR), 
	"FLUX_ID" NUMBER(19,0), 
	 PRIMARY KEY ("id")
  ) ;
 
ALTER TABLE "LISTE_ERREUR" ADD CONSTRAINT "FKrgbrtuxcdq6gve4vrbiy2te8s" FOREIGN KEY ("SIMULATION_ID")
	  REFERENCES "SIMULATION" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255289" ON "LISTE_ERREUR" ("id") 
   ;
;

CREATE TABLE "OAS_LINKHEAD_1" 
   (	"LINKCODE" VARCHAR2(12) NOT NULL ENABLE, 
	"CMPCODE" VARCHAR2(12) NOT NULL ENABLE, 
	"DOCCODE" VARCHAR2(12) NOT NULL ENABLE, 
	"DOCNUM" VARCHAR2(12) NOT NULL ENABLE, 
	"POSTED" NUMBER(5,0) NOT NULL ENABLE, 
	"YR" NUMBER(5,0), 
	"PERIOD" NUMBER(5,0), 
	"CURDOC" VARCHAR2(12), 
	"DOCDATE" DATE, 
	"AUTHUSER" VARCHAR2(12), 
	"DESCR" VARCHAR2(36), 
	"ORIGCMPCODE" VARCHAR2(12), 
	"ORIGDOCCODE" VARCHAR2(12), 
	"ORIGDOCNUM" VARCHAR2(12), 
	"POSTPONEWORKFLOW" NUMBER(5,0), 
	 CONSTRAINT "SYS_C00141578" CHECK ("LINKCODE" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00141579" CHECK ("CMPCODE" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00141580" CHECK ("DOCCODE" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00141581" CHECK ("DOCNUM" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00141582" CHECK ("POSTED" IS NOT NULL) ENABLE
   )  ;

-- CREATE UNIQUE INDEX "OAS_LINKHEAD_INDEX1" ON "OAS_LINKHEAD_1" ("LINKCODE", "CMPCODE", "DOCCODE", "DOCNUM") 
  
;

CREATE TABLE "OAS_LINKLINE_1" 
   (	"LINKCODE" VARCHAR2(12) NOT NULL ENABLE, 
	"CMPCODE" VARCHAR2(12) NOT NULL ENABLE, 
	"DOCCODE" VARCHAR2(12) NOT NULL ENABLE, 
	"DOCNUM" VARCHAR2(12) NOT NULL ENABLE, 
	"DOCLINENUM" NUMBER(10,0) NOT NULL ENABLE, 
	"ACCODE" VARCHAR2(79), 
	"VALUEDOC" NUMBER NOT NULL ENABLE, 
	"VALUEDOC_DP" NUMBER(5,0) NOT NULL ENABLE, 
	"DOCRATE" NUMBER, 
	"VALUEHOME" NUMBER, 
	"VALUEHOME_DP" NUMBER(5,0), 
	"VALUEDUAL" NUMBER, 
	"VALUEDUAL_DP" NUMBER(5,0), 
	"DUALRATE" NUMBER, 
	"STATUSER" CHAR(1), 
	"LINETYPE" NUMBER(5,0), 
	"DEBITCREDIT" NUMBER(5,0), 
	"DUEDATE" DATE, 
	"VALDATE" DATE, 
	"TAXCODE1" VARCHAR2(12), 
	"TAXVALUE1" NUMBER, 
	"TAXVALUE1_DP" NUMBER(5,0), 
	"TAXCODE2" VARCHAR2(12), 
	"TAXVALUE2" NUMBER, 
	"TAXVALUE2_DP" NUMBER(5,0), 
	"TAXCODE3" VARCHAR2(12), 
	"TAXVALUE3" NUMBER, 
	"TAXVALUE3_DP" NUMBER(5,0), 
	"TAXCODE4" VARCHAR2(12), 
	"TAXVALUE4" NUMBER, 
	"TAXVALUE4_DP" NUMBER(5,0), 
	"TAXCODE5" VARCHAR2(12), 
	"TAXVALUE5" NUMBER, 
	"TAXVALUE5_DP" NUMBER(5,0), 
	"TAXCODE6" VARCHAR2(12), 
	"TAXVALUE6" NUMBER, 
	"TAXVALUE6_DP" NUMBER(5,0), 
	"TAXCODE7" VARCHAR2(12), 
	"TAXVALUE7" NUMBER, 
	"TAXVALUE7_DP" NUMBER(5,0), 
	"TAXCODE8" VARCHAR2(12), 
	"TAXVALUE8" NUMBER, 
	"TAXVALUE8_DP" NUMBER(5,0), 
	"TAXCODE9" VARCHAR2(12), 
	"TAXVALUE9" NUMBER, 
	"TAXVALUE9_DP" NUMBER(5,0), 
	"DESCR" VARCHAR2(36), 
	"EXTREF1" VARCHAR2(32), 
	"EXTREF2" VARCHAR2(32), 
	"EXTREF3" VARCHAR2(32), 
	"EXTREF4" VARCHAR2(32), 
	"EXTREF5" VARCHAR2(32), 
	"EXTREF6" VARCHAR2(32), 
	"ELCURR1" VARCHAR2(12), 
	"ELVALUE1" NUMBER, 
	"ELVALUE1_DP" NUMBER(5,0), 
	"ELRATE1" NUMBER, 
	"ELCURR2" VARCHAR2(12), 
	"ELVALUE2" NUMBER, 
	"ELVALUE2_DP" NUMBER(5,0), 
	"ELRATE2" NUMBER, 
	"ELCURR3" VARCHAR2(12), 
	"ELVALUE3" NUMBER, 
	"ELVALUE3_DP" NUMBER(5,0), 
	"ELRATE3" NUMBER, 
	"ELCURR4" VARCHAR2(12), 
	"ELVALUE4" NUMBER, 
	"ELVALUE4_DP" NUMBER(5,0), 
	"ELRATE4" NUMBER, 
	"ELCURR5" VARCHAR2(12), 
	"ELVALUE5" NUMBER, 
	"ELVALUE5_DP" NUMBER(5,0), 
	"ELRATE5" NUMBER, 
	"ELCURR6" VARCHAR2(12), 
	"ELVALUE6" NUMBER, 
	"ELVALUE6_DP" NUMBER(5,0), 
	"ELRATE6" NUMBER, 
	"ELCURR7" VARCHAR2(12), 
	"ELVALUE7" NUMBER, 
	"ELVALUE7_DP" NUMBER(5,0), 
	"ELRATE7" NUMBER, 
	"ELCURR8" VARCHAR2(12), 
	"ELVALUE8" NUMBER, 
	"ELVALUE8_DP" NUMBER(5,0), 
	"ELRATE8" NUMBER, 
	"DISCSFLAG" NUMBER(5,0), 
	"DISCDATE1" DATE, 
	"DISCRATE1" NUMBER, 
	"DISCVALUE1" NUMBER, 
	"DISCVALUE1_DP" NUMBER(5,0), 
	"DISCDATE2" DATE, 
	"DISCRATE2" NUMBER, 
	"DISCVALUE2" NUMBER, 
	"DISCVALUE2_DP" NUMBER(5,0), 
	"DISCDATE3" DATE, 
	"DISCRATE3" NUMBER, 
	"DISCVALUE3" NUMBER, 
	"DISCVALUE3_DP" NUMBER(5,0), 
	"DISCDATE4" DATE, 
	"DISCRATE4" NUMBER, 
	"DISCVALUE4" NUMBER, 
	"DISCVALUE4_DP" NUMBER(5,0), 
	"DISCDATE5" DATE, 
	"DISCRATE5" NUMBER, 
	"DISCVALUE5" NUMBER, 
	"DISCVALUE5_DP" NUMBER(5,0), 
	"DOCSUMTAX" NUMBER, 
	"DOCSUMTAX_DP" NUMBER(5,0), 
	"TAXLINECODE" VARCHAR2(12), 
	"DOCTAXTURN" NUMBER, 
	"DOCTAXTURN_DP" NUMBER(5,0), 
	"TEN99TAXCODE1" VARCHAR2(12), 
	"TEN99TAXVALUE1" NUMBER, 
	"TEN99TAXVALUE1_DP" NUMBER(5,0), 
	"TEN99TAXCODE2" VARCHAR2(12), 
	"TEN99TAXVALUE2" NUMBER, 
	"TEN99TAXVALUE2_DP" NUMBER(5,0), 
	"TEN99TAXCODE3" VARCHAR2(12), 
	"TEN99TAXVALUE3" NUMBER, 
	"TEN99TAXVALUE3_DP" NUMBER(5,0), 
	"MEDCODE" VARCHAR2(12), 
	"BNKCODE" VARCHAR2(12), 
	"ELMBANKTAG" NUMBER(5,0), 
	"ELMADDRTAG" NUMBER(5,0), 
	"USRREF1" VARCHAR2(35), 
	"USRREF2" VARCHAR2(35), 
	"USRREF3" VARCHAR2(35), 
	"DESTCODE" VARCHAR2(12), 
	"PARENTCURR" VARCHAR2(12), 
	"PARENTVALUE" NUMBER, 
	"PARENTVALUE_DP" NUMBER(5,0), 
	"PARENTRATE" NUMBER, 
	"ASSETCODE" VARCHAR2(79), 
	"CATCODE" VARCHAR2(12), 
	"ASSETFLAGS" NUMBER(10,0), 
	"CAPDATE" DATE, 
	"DEPNSTARTDATE" DATE, 
	 CONSTRAINT "SYS_C00141583" CHECK ("LINKCODE" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00141584" CHECK ("CMPCODE" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00141585" CHECK ("DOCCODE" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00141586" CHECK ("DOCNUM" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00141587" CHECK ("DOCLINENUM" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00141588" CHECK ("VALUEDOC" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00141589" CHECK ("VALUEDOC_DP" IS NOT NULL) ENABLE
   ) ;

-- CREATE UNIQUE INDEX "OAS_LINKLINE_INDEX" ON "OAS_LINKLINE_1" ("CMPCODE", "LINKCODE", "DOCCODE", "DOCNUM", "DOCLINENUM") 
 
;

CREATE TABLE "OAS_LINKQTY_1" 
   (	"CMPCODE" VARCHAR2(12) NOT NULL ENABLE, 
	"DOCCODE" VARCHAR2(12) NOT NULL ENABLE, 
	"DOCNUM" VARCHAR2(12) NOT NULL ENABLE, 
	"DOCLINENUM" NUMBER(10,0) NOT NULL ENABLE, 
	"ELMLEVEL" NUMBER(5,0) NOT NULL ENABLE, 
	"QTYPOSITION" NUMBER(5,0) NOT NULL ENABLE, 
	"QTYVALUE" NUMBER NOT NULL ENABLE, 
	"QTYVALUE_DP" NUMBER(5,0) NOT NULL ENABLE, 
	 CONSTRAINT "SYS_C00139502" CHECK ("CMPCODE" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00139503" CHECK ("DOCCODE" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00139504" CHECK ("DOCNUM" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00139505" CHECK ("DOCLINENUM" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00139506" CHECK ("ELMLEVEL" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00139507" CHECK ("QTYPOSITION" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00139508" CHECK ("QTYVALUE" IS NOT NULL) ENABLE, 
	 CONSTRAINT "SYS_C00139509" CHECK ("QTYVALUE_DP" IS NOT NULL) ENABLE
   ) ;

-- CREATE UNIQUE INDEX "OAS_LINKQTY_INDEX1" ON "OAS_LINKQTY_1" ("CMPCODE", "DOCCODE", "DOCNUM", "DOCLINENUM", "ELMLEVEL", "QTYPOSITION") 
 
;

CREATE TABLE "PARAMETRAGE_COMPTE" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"ACCOUNT_DESCRIPTION" VARCHAR2(255 CHAR), 
	"ACCOUNT_NUMBER" VARCHAR2(255 CHAR), 
	"SENS" CHAR(1 CHAR), 
	"TWA" NUMBER(1,0), 
	"ID_SCHEMA" NUMBER(19,0), 
	 PRIMARY KEY ("ID")
 ) ;

CREATE TABLE "PARAMETRAGE_SCHEMA_COMPTABLE" 
   (	"ID_SCHEMA" NUMBER(19,0) NOT NULL ENABLE, 
	"CODE" VARCHAR2(255 CHAR), 
	"CODE_ANALYSE" VARCHAR2(255 CHAR), 
	"CODE_FOURNISSEUR" VARCHAR2(255 CHAR), 
	"COST_CENTER" VARCHAR2(255 CHAR), 
	"DESCRIPTION" VARCHAR2(255 CHAR), 
	"FLUX_ID" NUMBER(19,0), 
	"COMPTE_CREDITEUR" VARCHAR2(255 CHAR), 
	"COMPTE_DEBITEUR" VARCHAR2(255 CHAR), 
	"COMPTE_TVA" VARCHAR2(255 CHAR), 
	 PRIMARY KEY ("ID_SCHEMA")
);

ALTER TABLE "PARAMETRAGE_COMPTE" ADD CONSTRAINT "FKlne9h1ywfv5kj4x6j8a1hqfxv" FOREIGN KEY ("ID_SCHEMA")
	  REFERENCES "PARAMETRAGE_SCHEMA_COMPTABLE" ("ID_SCHEMA") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255291" ON "PARAMETRAGE_COMPTE" ("ID") 
 
;

CREATE TABLE "PARAMETRAGE_COMPTE_BY" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"ACCOUNT_DESCRIPTION" VARCHAR2(255 CHAR), 
	"ACCOUNT_NUMBER" VARCHAR2(255 CHAR), 
	"ACTIVE" VARCHAR2(255 CHAR), 
	"Complement" NUMBER(1,0), 
	"CONFIGURED_BY" VARCHAR2(255 CHAR), 
	"FLUX" VARCHAR2(255 CHAR), 
	"PAYMENT_MODE" VARCHAR2(255 CHAR), 
	"SENS" CHAR(1 CHAR), 
	"SITE" VARCHAR2(255 CHAR), 
	 PRIMARY KEY ("ID")
);

-- CREATE UNIQUE INDEX "SYS_C00255417" ON "PARAMETRAGE_COMPTE_BY" ("ID") 
 


-- CREATE UNIQUE INDEX "SYS_C00255293" ON "PARAMETRAGE_FLUX" ("id") 

;

CREATE TABLE "PARAMETRAGE_FLUX_FIELD" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"codeBDD" VARCHAR2(255 CHAR), 
	"code_entite" VARCHAR2(255 CHAR), 
	"description" VARCHAR2(255 CHAR), 
	"ordre" NUMBER(10,0), 
	"visible" NUMBER(1,0), 
	"FLUX_ID" NUMBER(19,0), 
	 PRIMARY KEY ("id")
 );

ALTER TABLE "PARAMETRAGE_FLUX_FIELD" ADD CONSTRAINT "FKk8pst69v0vr5osnjse0yxhww3" FOREIGN KEY ("FLUX_ID")
	  REFERENCES "PARAMETRAGE_FLUX" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255295" ON "PARAMETRAGE_FLUX_FIELD" ("id") 

ALTER TABLE "PARAMETRAGE_SCHEMA_COMPTABLE" ADD CONSTRAINT "FKb1etjb7snc83d7cttdjk08hrp" FOREIGN KEY ("FLUX_ID")
	  REFERENCES "PARAMETRAGE_FLUX" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255297" ON "PARAMETRAGE_SCHEMA_COMPTABLE" ("ID_SCHEMA") 
 ;
;

CREATE TABLE "PARAM_CLIENT" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"CLIENT" VARCHAR2(255 CHAR), 
	"CODE_FINANCE" VARCHAR2(255 CHAR), 
	"CODE_METIER" VARCHAR2(255 CHAR), 
	"DESCRIPTION" VARCHAR2(255 CHAR), 
	"SITE" VARCHAR2(255 CHAR), 
	 PRIMARY KEY ("ID")
 ) ;

-- CREATE UNIQUE INDEX "SYS_C00255419" ON "PARAM_CLIENT" ("ID") 
  ;
;

CREATE TABLE "PARAM_MAPPING" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"ADDITIONAL_FIELD" VARCHAR2(255 CHAR), 
	"ADDITIONAL_FIELD_DESCR" VARCHAR2(255 CHAR), 
	"CATEGORIE" VARCHAR2(255 CHAR), 
	"CODE_FINANCE" VARCHAR2(255 CHAR), 
	"CODE_METIER" VARCHAR2(255 CHAR), 
	"DESCRIPTION" VARCHAR2(255 CHAR), 
	 PRIMARY KEY ("ID")
 );

-- CREATE UNIQUE INDEX "SYS_C00255299" ON "PARAM_MAPPING" ("ID") 
 
;

CREATE TABLE "PARAM_SUPPLIER" 
   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
	"CODE_FINANCE" VARCHAR2(255 CHAR), 
	"CODE_METIER" VARCHAR2(255 CHAR), 
	"CURRENCY" VARCHAR2(255 CHAR), 
	"DESCRIPTION" VARCHAR2(255 CHAR), 
	"TYPE" VARCHAR2(255 CHAR), 
	 PRIMARY KEY ("ID")
);

-- CREATE UNIQUE INDEX "SYS_C00255301" ON "PARAM_SUPPLIER" ("ID") 
 ;


ALTER TABLE "PIECE_COMPTABLE" ADD CONSTRAINT "FKahk69gawkyhb6a92mnl6vy4tl" FOREIGN KEY ("SIMULATION_ID")
	  REFERENCES "SIMULATION" ("id") ENABLE;
 
  ALTER TABLE "PIECE_COMPTABLE" ADD CONSTRAINT "FKie5p68tta9n8o3o6jpmvqmkvl" FOREIGN KEY ("FLUX_ID")
	  REFERENCES "PARAMETRAGE_FLUX" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255303" ON "PIECE_COMPTABLE" ("id") 
   ;

ALTER TABLE "SIMULATION" ADD CONSTRAINT "FK7jpu3rs5u06er0reuk7v7685a" FOREIGN KEY ("FLUX_ID")
	  REFERENCES "PARAMETRAGE_FLUX" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255305" ON "SIMULATION" ("id") 
 ;
;

CREATE TABLE "SIMULATION_CDCOH" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"HEADER_ID" NUMBER(19,0), 
	"SIMULATION_ID" NUMBER(19,0), 
	 PRIMARY KEY ("id")
) ;

ALTER TABLE "SIMULATION_CDCOH" ADD CONSTRAINT "FKgvygba3uc0t1f5ml5n0q14dea" FOREIGN KEY ("SIMULATION_ID")
	  REFERENCES "SIMULATION" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255307" ON "SIMULATION_CDCOH" ("id") 
  ;
;

CREATE TABLE "SIMULATION_CDDEL" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"DELIVERY_ID" NUMBER(19,0), 
	"SIMULATION_ID" NUMBER(19,0), 
	 PRIMARY KEY ("id")
 ) ;

ALTER TABLE "SIMULATION_CDDEL" ADD CONSTRAINT "FKmsimsufbd6rvybr7lfbm8ot7j" FOREIGN KEY ("SIMULATION_ID")
	  REFERENCES "SIMULATION" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255309" ON "SIMULATION_CDDEL" ("id") 
 ;
;

CREATE TABLE "SIMULATION_CDDEP" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"VEHICULE_ID" NUMBER(19,0), 
	"SIMULATION_ID" NUMBER(19,0), 
	 PRIMARY KEY ("id")
 ) ;

ALTER TABLE "SIMULATION_CDDEP" ADD CONSTRAINT "FKqfnn2nm46kw7ql937t032yh2j" FOREIGN KEY ("SIMULATION_ID")
	  REFERENCES "SIMULATION" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255311" ON "SIMULATION_CDDEP" ("id") 
  ;
;

CREATE TABLE "SIMULATION_CDFIH" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"SIMULATION_ID" NUMBER(19,0), 
	"HEADER_ID" NUMBER(19,0), 
	 PRIMARY KEY ("id")
  ) ;

ALTER TABLE "SIMULATION_CDFIH" ADD CONSTRAINT "FKl1fia83oftcys4925s88iedv1" FOREIGN KEY ("SIMULATION_ID")
	  REFERENCES "SIMULATION" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255313" ON "SIMULATION_CDFIH" ("id") 
 
;

CREATE TABLE "SIMULATION_CDINV" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"VEHICULE_ID" NUMBER(19,0), 
	"SIMULATION_ID" NUMBER(19,0), 
	 PRIMARY KEY ("id")
  );

ALTER TABLE "SIMULATION_CDINV" ADD CONSTRAINT "FK1evrdsafawdmpshvq1s9ohw1h" FOREIGN KEY ("SIMULATION_ID")
	  REFERENCES "SIMULATION" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255315" ON "SIMULATION_CDINV" ("id") 
   ;
;

CREATE TABLE "SIMULATION_CDMOV" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"LOGISTIC_OPERATIONS_ID" NUMBER(19,0), 
	"SIMULATION_ID" NUMBER(19,0), 
	 PRIMARY KEY ("id")
 ) ;

ALTER TABLE "SIMULATION_CDMOV" ADD CONSTRAINT "FK3ltki43knjsh1rx15xeljajhc" FOREIGN KEY ("SIMULATION_ID")
	  REFERENCES "SIMULATION" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255317" ON "SIMULATION_CDMOV" ("id") 
  ;
;

CREATE TABLE "SIMULATION_CDORD" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"SIMULATION_ID" NUMBER(19,0), 
	"VEHICULE_ID" NUMBER(19,0), 
	 PRIMARY KEY ("id")
  ) ;

ALTER TABLE "SIMULATION_CDORD" ADD CONSTRAINT "FKb678h0xomtxxb2yfqiijrtww7" FOREIGN KEY ("SIMULATION_ID")
	  REFERENCES "SIMULATION" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255319" ON "SIMULATION_CDORD" ("id") 
  
;

CREATE TABLE "SIMULATION_CDPOH" 
   (	"id" NUMBER(19,0) NOT NULL ENABLE, 
	"HEADER_ID" NUMBER(19,0), 
	"SIMULATION_ID" NUMBER(19,0), 
	 PRIMARY KEY ("id")
  ) ;

ALTER TABLE "SIMULATION_CDPOH" ADD CONSTRAINT "FKrpsm1kxnlak74k4n1m8tc858u" FOREIGN KEY ("SIMULATION_ID")
	  REFERENCES "SIMULATION" ("id") ENABLE;

-- CREATE UNIQUE INDEX "SYS_C00255321" ON "SIMULATION_CDPOH" ("id") 
  
;

CREATE SEQUENCE "hibernate_sequence" INCREMENT BY 1 MINVALUE 1 MAXVALUE 999999999999999999999999999 NOCYCLE CACHE 20 NOORDER ;

CREATE SEQUENCE VEHICLE_FINALISATION_HEAD_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 999999999999999999999999999 NOCYCLE CACHE 20 NOORDER ;

CREATE SEQUENCE VEHICLE_FIN_DETAIL_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 999999999999999999999999999 NOCYCLE CACHE 20 NOORDER ;
