WITH RankedSalaries AS (
    SELECT
        name,
        salary,
        departmentId,
        -- 1. DENSE_RANK() 윈도우 함수 적용
        DENSE_RANK() OVER (
            PARTITION BY departmentId 
            ORDER BY salary DESC
        ) AS rnk
    FROM
        Employee
)
-- 2. CTE와 Department 테이블 조인
SELECT
    d.name AS Department,
    rs.name AS Employee,
    rs.salary AS Salary
FROM
    RankedSalaries rs
JOIN
    Department d ON rs.departmentId = d.id
WHERE
    -- 3. 순위가 3 이하인 경우만 필터링
    rs.rnk <= 3;