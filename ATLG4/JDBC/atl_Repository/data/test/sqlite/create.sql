----------------------
-- Créer les tables
----------------------

CREATE TABLE STUDENTS (
	id			INTEGER 	PRIMARY KEY AUTOINCREMENT,
	firstname	TEXT		NOT NULL,
	lastname	TEXT		NOT NULL
	);	

CREATE TABLE LESSONS (
	acronym		TEXT 	PRIMARY KEY
	);	
	
CREATE TABLE GRADES (
	id			INTEGER PRIMARY KEY AUTOINCREMENT,
	score		INTEGER,
	date		TEXT	NOT NULL,
	dateModified  	TEXT	NOT NULL,
	id_student		INTEGER	NOT NULL,
	id_lesson		TEXT	NOT NULL,
	FOREIGN KEY(id_student) REFERENCES STUDENTS(id),
	FOREIGN KEY(id_lesson) 	REFERENCES LESSONS(acronym)
	);	
	