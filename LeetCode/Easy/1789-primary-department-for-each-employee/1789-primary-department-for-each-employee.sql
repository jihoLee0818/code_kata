-- 규칙 1: primary_flag가 'Y'인 경우
SELECT
    employee_id,
    department_id
FROM
    Employee
WHERE
    primary_flag = 'Y'

UNION

-- 규칙 2: 소속된 부서가 1개뿐인 경우
SELECT
    employee_id,
    department_id
FROM
    Employee
GROUP BY
    employee_id
HAVING
    COUNT(employee_id) = 1;