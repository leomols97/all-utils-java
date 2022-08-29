----------------------
-- Ajouter des données
----------------------
INSERT INTO STUDENTS ("firstname","lastname") VALUES ("Maggy","Olsen");
INSERT INTO STUDENTS ("firstname","lastname") VALUES ("Phoebe","Frost");
INSERT INTO STUDENTS ("firstname","lastname") VALUES ("Skyler","Ortega");
INSERT INTO STUDENTS ("firstname","lastname") VALUES ("Byron","Blankenship");
INSERT INTO STUDENTS ("firstname","lastname") VALUES ("Molly","Cote");
INSERT INTO STUDENTS ("firstname","lastname") VALUES ("SpongeBob","SquarePants");

INSERT INTO LESSONS(acronym) VALUES ('ATL');
INSERT INTO LESSONS(acronym) VALUES ('PER');
INSERT INTO LESSONS(acronym) VALUES ('WEB');
INSERT INTO LESSONS(acronym) VALUES ('CPPL');
INSERT INTO LESSONS(acronym) VALUES ('CPRL');
INSERT INTO LESSONS(acronym) VALUES ('SECL');
INSERT INTO LESSONS(acronym) VALUES ('SYS');

INSERT INTO GRADES ("score", "date", "dateModified", "id_student", "id_lesson") VALUES (14, '2020-04-01', '2020-05-01 10:27:33', 1, 'ATL');
INSERT INTO GRADES ("score", "date", "dateModified", "id_student", "id_lesson") VALUES (16, '2020-05-01', '2020-05-01 10:27:33', 1, 'ATL');
INSERT INTO GRADES ("score", "date", "dateModified", "id_student", "id_lesson") VALUES (10, '2020-05-15', '2020-05-01 10:27:33', 1, 'ATL');
INSERT INTO GRADES ("score", "date", "dateModified", "id_student", "id_lesson") VALUES (14, '2020-05-01', '2020-05-01 10:27:33', 2, 'ATL');
INSERT INTO GRADES ("score", "date", "dateModified", "id_student", "id_lesson") VALUES (14, '2020-05-15', '2020-05-01 10:27:33', 2, 'ATL');




