-- Run this in your PostgreSQL client after the Java application runs.
-- The application uses ID 1001.

SELECT id, name, email, course
FROM student
WHERE id = 1001;

-- Expected result after the complete application run:
-- id   | name       | email                          | course
-- 1001 | Meenakshi  | meenakshi1001@example.com      | Computer Engineering
