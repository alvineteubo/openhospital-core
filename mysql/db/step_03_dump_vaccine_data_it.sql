delete from VACCINE;
delete from VACCINETYPE;

-- VACCINETYPE
LOAD DATA LOCAL INFILE './data_it/vaccinetype.csv'
	INTO TABLE VACCINETYPE 
	FIELDS TERMINATED BY ';' 
	LINES TERMINATED BY '\n';
	
-- VACCINE
LOAD DATA LOCAL INFILE './data_it/vaccine.csv'
	INTO TABLE VACCINE 
	FIELDS TERMINATED BY ';' 
	LINES TERMINATED BY '\n';