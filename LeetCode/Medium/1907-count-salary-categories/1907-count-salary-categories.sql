-- 'Low Salary' 카테고리 계산
SELECT
    'Low Salary' AS category,
    COUNT(*) AS accounts_count
FROM
    Accounts
WHERE
    income < 20000

UNION

-- 'Average Salary' 카테고리 계산
SELECT
    'Average Salary' AS category,
    COUNT(*) AS accounts_count
FROM
    Accounts
WHERE
    income BETWEEN 20000 AND 50000

UNION

-- 'High Salary' 카테고리 계산
SELECT
    'High Salary' AS category,
    COUNT(*) AS accounts_count
FROM
    Accounts
WHERE
    income > 50000;