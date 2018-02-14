-- Departments
INSERT INTO "department" (id, name, city) VALUES (1, 'Big Central', 'Moscow');
INSERT INTO "department" (id, name, city) VALUES (2, 'On River', 'St. Peterburg');
INSERT INTO "department" (id, name, city) VALUES (3, 'Small Town', 'Vologda');
SELECT setval('department_id_seq', 3);
-- Positions
INSERT INTO "position" (id, name, salary) VALUES (1, 'Big boss', 1000);
INSERT INTO "position" (id, name, salary) VALUES (2, 'Infernal manager', 666);
INSERT INTO "position" (id, name, salary) VALUES (3, 'Janitor', 10.12);
INSERT INTO "position" (id, name, salary) VALUES (4, 'Manager', 250);
INSERT INTO "position" (id, name, salary) VALUES (5, 'Fat Manager', 300);
SELECT setval('position_id_seq', 5);
-- Employers
INSERT INTO "employee" (id, lastname, firstname, middlename, login, password, department_id, position_id)
VALUES (1, 'lastname1', 'firstname1', 'middlename1', 'login1', 'password1', 1, 1);
INSERT INTO "employee" (id, lastname, firstname, middlename, login, password, department_id, position_id)
VALUES (2, 'lastname2', 'firstname2', 'middlename2', 'login2', 'password2', 2, 2);
INSERT INTO "employee" (id, lastname, firstname, middlename, login, password, department_id, position_id)
VALUES (3, 'lastname3', 'firstname3', 'middlename3', 'login3', 'password3', 3, 2);
INSERT INTO "employee" (id, lastname, firstname, middlename, login, password, department_id, position_id)
VALUES (4, 'lastname4', 'firstname4', 'middlename4', 'login4', 'password4', 1, 3);
INSERT INTO "employee" (id, lastname, firstname, middlename, login, password, department_id, position_id)
VALUES (5, 'lastname5', 'firstname5', 'middlename5', 'login5', 'password5', 2, 3);
INSERT INTO "employee" (id, lastname, firstname, middlename, login, password, department_id, position_id)
VALUES (6, 'lastname6', 'firstname6', 'middlename6', 'login6', 'password6', 3, 4);
INSERT INTO "employee" (id, lastname, firstname, middlename, login, password, department_id, position_id)
VALUES (7, 'lastname7', 'firstname7', 'middlename7', 'login7', 'password7', 1, 4);
INSERT INTO "employee" (id, lastname, firstname, middlename, login, password, department_id, position_id)
VALUES (8, 'lastname8', 'firstname8', 'middlename8', 'login8', 'password8', 2, 4);
INSERT INTO "employee" (id, lastname, firstname, middlename, login, password, department_id, position_id)
VALUES (9, 'lastname9', 'firstname9', 'middlename9', 'login9', 'password9', 3, 4);
INSERT INTO "employee" (id, lastname, firstname, middlename, login, password, department_id, position_id)
VALUES (10, 'lastname10', 'firstname10', 'middlename10', 'login10', 'password10', 1, 5);
INSERT INTO "employee" (id, lastname, firstname, middlename, login, password, department_id, position_id)
VALUES (11, 'lastname11', 'firstname11', 'middlename11', 'login11', 'password11', 2, 5);
INSERT INTO "employee" (id, lastname, firstname, middlename, login, password, department_id, position_id)
VALUES (12, 'lastname12', 'firstname12', 'middlename12 ', 'login12', 'password12', 3, 5);
SELECT setval('employee_id_seq', 15);
