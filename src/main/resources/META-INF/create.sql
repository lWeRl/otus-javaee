CREATE TABLE IF NOT EXISTS "department" (id BIGSERIAL PRIMARY KEY NOT NULL,name VARCHAR(20) NOT NULL,city VARCHAR(20) NOT NULL);
CREATE TABLE IF NOT EXISTS "position" (id BIGSERIAL PRIMARY KEY NOT NULL,name VARCHAR(20) NOT NULL,salary FLOAT NOT NULL);
CREATE TABLE IF NOT EXISTS "employee" (id BIGSERIAL PRIMARY KEY NOT NULL,lastName VARCHAR(20) NOT NULL,firstName VARCHAR(20) NOT NULL,middleName VARCHAR(20) NOT NULL,login VARCHAR(20) NOT NULL,password VARCHAR(20) NOT NULL,department_id BIGINT REFERENCES department(id),position_id BIGINT REFERENCES position(id));

CREATE OR REPLACE FUNCTION getMaxSalaryName() RETURNS VARCHAR AS $$ DECLARE result varchar; BEGIN SELECT lastName INTO result FROM employee JOIN "position" ON employee.position_id = position.id WHERE "position".salary = (SELECT max(salary) FROM "position"); RETURN result; END $$ LANGUAGE plpgsql;
