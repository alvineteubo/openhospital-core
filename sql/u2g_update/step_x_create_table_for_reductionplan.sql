
--
-- Definition of table oh_reductionplan
--

CREATE TABLE oh_reductionplan (
  RP_ID INT(11) NOT NULL AUTO_INCREMENT,
  RP_DESCRIPTION VARCHAR(100) NOT NULL,
  RP_OPERATIONRATE FLOAT NOT NULL,
  RP_MEDICALRATE FLOAT NOT NULL,
  RP_EXAMRATE FLOAT NOT NULL,
  RP_OTHERRATE FLOAT NOT NULL,
  RP_CREATED_BY VARCHAR(50),
  RP_CREATED_DATE DATETIME,
  RP_LAST_MODIFIED_BY VARCHAR(50),
  RP_LAST_MODIFIED_DATE DATETIME,
  RP_ACTIVE TINYINT(1) NOT NULL DEFAULT 1,
  RP_LOCK INT(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (RP_ID),
  KEY RP_CREATED_BY (RP_CREATED_BY),
  KEY RP_LAST_MODIFIED_BY (RP_LAST_MODIFIED_BY)
) ENGINE=MyISAM;


--
-- Definition of table oh_otherreduction
--

CREATE TABLE oh_otherreduction (
  OTR_RP_ID INT(11) NOT NULL,
  OTR_OTH_ID INT(11) NOT NULL,
  OTR_REDUCTIONRATE FLOAT NOT NULL,
  PRIMARY KEY (OTR_RP_ID, OTR_OTH_ID)
)ENGINE=MyISAM;;

--
-- Definition of table oh_exampsreduction
--

CREATE TABLE oh_examsreduction (
  ER_RP_ID INT(11) NOT NULL,
  ER_EXA_ID_A VARCHAR(10) NOT NULL,
  ER_REDUCTIONRATE FLOAT NOT NULL,
  PRIMARY KEY (ER_RP_ID, ER_EXA_ID_A)
)ENGINE=MyISAM;;


--
-- Definition of table oh_medicalsreduction
--
CREATE TABLE oh_medicalsreduction (
  MR_RP_ID INT(11) NOT NULL,
  MR_MED_ID INT(11) NOT NULL,
  MR_REDUCTIONRATE FLOAT NOT NULL,
  PRIMARY KEY (MR_RP_ID, MR_MED_ID)
)ENGINE=MyISAM;;


--
-- Definition of table oh_operationsreduction
--

CREATE TABLE oh_operationsreduction (
  OPR_RP_ID INT(11) NOT NULL,
  OPR_OPE_ID_A VARCHAR(10) NOT NULL,
  OPR_REDUCTIONRATE FLOAT NOT NULL,
  PRIMARY KEY (OPR_RP_ID, OPR_OPE_ID_A)
)ENGINE=MyISAM;;


