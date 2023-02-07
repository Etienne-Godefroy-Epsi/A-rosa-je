USE A_ROSA_JE;
# -----------------------------------------------------------------------------
#       TABLE : UTILISATEUR
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS UTILISATEUR
 (
   ID_UTILISATEUR INTEGER(2) NOT NULL  ,
   MDP VARCHAR(128) NULL  ,
   NOM VARCHAR(128) NULL  ,
   PRENOM VARCHAR(128) NULL  ,
   ADRESSE VARCHAR(128) NULL  ,
   MAIL VARCHAR(128) NULL  ,
   DESCRIPTION VARCHAR(128) NULL  ,
   TYPE CHAR(32) NULL  
   , PRIMARY KEY (ID_UTILISATEUR) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : PHOTOJOURNALIERE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PHOTOJOURNALIERE
 (
   ID_PHOTO INTEGER(2) NOT NULL  ,
   ID_PLANTEAGARDER INTEGER(2) NOT NULL  ,
   LIENPHOTO VARCHAR(128) NULL  ,
   DATEJOUR DATE NULL  
   , PRIMARY KEY (ID_PHOTO) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE PHOTOJOURNALIERE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_PHOTOJOURNALIERE_PLANTEAGARDER
     ON PHOTOJOURNALIERE (ID_PLANTEAGARDER ASC);

# -----------------------------------------------------------------------------
#       TABLE : PLANTE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PLANTE
 (
   ID_PLANTE INTEGER(2) NOT NULL  ,
   NOMPLANTE VARCHAR(128) NULL  ,
   TYPEPLANTE VARCHAR(128) NULL  ,
   PHOTOGENERIC VARCHAR(128) NULL  
   , PRIMARY KEY (ID_PLANTE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : CONTRAT
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS CONTRAT
 (
   ID_CONTRAT INTEGER(2) NOT NULL  ,
   DATEDEBUT DATE NULL  ,
   DATEFIN DATE NULL  ,
   ETATCONTRAT CHAR(32) NULL  ,
   ID_CLIENT INTEGER(2) NOT NULL  ,
   ID_GARDIEN INTEGER(2) NOT NULL  ,
   ID_BOTANISTE INTEGER(2) NOT NULL  
   , PRIMARY KEY (ID_CONTRAT) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE CONTRAT
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_CONTRAT_UTILISATEUR
     ON CONTRAT (ID_CLIENT ASC);

CREATE  INDEX I_FK_CONTRAT_UTILISATEUR1
     ON CONTRAT (ID_GARDIEN ASC);

CREATE  INDEX I_FK_CONTRAT_UTILISATEUR3
     ON CONTRAT (ID_BOTANISTE ASC);

# -----------------------------------------------------------------------------
#       TABLE : PLANTEAGARDER
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PLANTEAGARDER
 (
   ID_PLANTEAGARDER INTEGER(2) NOT NULL  ,
   ID_CONTRAT INTEGER(2) NOT NULL  ,
   ID_PLANTE INTEGER(2) NOT NULL  ,
   QUANTITE INTEGER(2) NULL  
   , PRIMARY KEY (ID_PLANTEAGARDER) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE PLANTEAGARDER
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_PLANTEAGARDER_CONTRAT
     ON PLANTEAGARDER (ID_CONTRAT ASC);

CREATE  INDEX I_FK_PLANTEAGARDER_PLANTE
     ON PLANTEAGARDER (ID_PLANTE ASC);


# -----------------------------------------------------------------------------
#       CREATION DES REFERENCES DE TABLE
# -----------------------------------------------------------------------------


ALTER TABLE PHOTOJOURNALIERE 
  ADD FOREIGN KEY FK_PHOTOJOURNALIERE_PLANTEAGARDER (ID_PLANTEAGARDER)
      REFERENCES PLANTEAGARDER (ID_PLANTEAGARDER) ;


ALTER TABLE CONTRAT 
  ADD FOREIGN KEY FK_CONTRAT_UTILISATEUR (ID_CLIENT)
      REFERENCES UTILISATEUR (ID_UTILISATEUR) ;


ALTER TABLE CONTRAT 
  ADD FOREIGN KEY FK_CONTRAT_UTILISATEUR1 (ID_GARDIEN)
      REFERENCES UTILISATEUR (ID_UTILISATEUR) ;


ALTER TABLE CONTRAT 
  ADD FOREIGN KEY FK_CONTRAT_UTILISATEUR3 (ID_BOTANISTE)
      REFERENCES UTILISATEUR (ID_UTILISATEUR) ;


ALTER TABLE PLANTEAGARDER 
  ADD FOREIGN KEY FK_PLANTEAGARDER_CONTRAT (ID_CONTRAT)
      REFERENCES CONTRAT (ID_CONTRAT) ;


ALTER TABLE PLANTEAGARDER 
  ADD FOREIGN KEY FK_PLANTEAGARDER_PLANTE (ID_PLANTE)
      REFERENCES PLANTE (ID_PLANTE) ;

