SELECT
    employee_id
FROM
    Employees
WHERE
    salary < 30000
    AND manager_id IS NOT NULL
    AND manager_id NOT IN (
        -- Employees 테이블에 존재하는 모든 직원(매니저) ID 목록
        SELECT employee_id FROM Employees
    )
ORDER BY
    employee_id;