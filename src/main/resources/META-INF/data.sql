-- Departments
INSERT INTO "department" (name, city) VALUES ('Big Central', 'Moscow');
INSERT INTO "department" (name, city) VALUES ('On River', 'St. Peterburg');
INSERT INTO "department" (name, city) VALUES ('Small Town', 'Vologda');
-- Positions
INSERT INTO "position" (name, salary) VALUES ('Big boss', 1000);
INSERT INTO "position" (name, salary) VALUES ('Infernal manager', 666);
INSERT INTO "position" (name, salary) VALUES ('Janitor', 10.12);
INSERT INTO "position" (name, salary) VALUES ('Manager', 250);
INSERT INTO "position" (name, salary) VALUES ('Fat Manager', 300);
-- Employers
INSERT INTO "employee" (lastname, firstname, middlename, login, password, department_id, position_id)
VALUES ('admin', 'admin', 'admin', 'admin', 'admin', 1, 1);
INSERT INTO "employee" (lastname, firstname, middlename, login, password, department_id, position_id)
VALUES ('lastname1', 'firstname1', 'middlename1', 'login1', 'password1', 1, 1);
INSERT INTO "employee" (lastname, firstname, middlename, login, password, department_id, position_id)
VALUES ('lastname2', 'firstname2', 'middlename2', 'login2', 'password2', 2, 2);
INSERT INTO "employee" (lastname, firstname, middlename, login, password, department_id, position_id)
VALUES ('lastname3', 'firstname3', 'middlename3', 'login3', 'password3', 3, 2);
INSERT INTO "employee" (lastname, firstname, middlename, login, password, department_id, position_id)
VALUES ('lastname4', 'firstname4', 'middlename4', 'login4', 'password4', 1, 3);
INSERT INTO "employee" (lastname, firstname, middlename, login, password, department_id, position_id)
VALUES ('lastname5', 'firstname5', 'middlename5', 'login5', 'password5', 2, 3);
INSERT INTO "employee" (lastname, firstname, middlename, login, password, department_id, position_id)
VALUES ('lastname6', 'firstname6', 'middlename6', 'login6', 'password6', 3, 4);
INSERT INTO "employee" (lastname, firstname, middlename, login, password, department_id, position_id)
VALUES ('lastname7', 'firstname7', 'middlename7', 'login7', 'password7', 1, 4);
INSERT INTO "employee" (lastname, firstname, middlename, login, password, department_id, position_id)
VALUES ('lastname8', 'firstname8', 'middlename8', 'login8', 'password8', 2, 4);
INSERT INTO "employee" (lastname, firstname, middlename, login, password, department_id, position_id)
VALUES ('lastname9', 'firstname9', 'middlename9', 'login9', 'password9', 3, 4);
INSERT INTO "employee" (lastname, firstname, middlename, login, password, department_id, position_id)
VALUES ('lastname10', 'firstname10', 'middlename10', 'login10', 'password10', 1, 5);
INSERT INTO "employee" (lastname, firstname, middlename, login, password, department_id, position_id)
VALUES ('lastname11', 'firstname11', 'middlename11', 'login11', 'password11', 2, 5);
INSERT INTO "employee" (lastname, firstname, middlename, login, password, department_id, position_id)
VALUES ('lastname12', 'firstname12', 'middlename12 ', 'login12', 'password12', 3, 5);
