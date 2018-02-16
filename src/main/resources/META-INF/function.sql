CREATE OR REPLACE FUNCTION getMaxSalaryName()
  RETURNS VARCHAR AS $FUNC$ DECLARE result VARCHAR;
BEGIN SELECT lastName
      INTO result
      FROM employee
        JOIN "position" ON employee.position_id = position.id
      WHERE "position".salary = (SELECT max(salary)
                                 FROM "position");
  RETURN result;
END $FUNC$ LANGUAGE plpgsql;