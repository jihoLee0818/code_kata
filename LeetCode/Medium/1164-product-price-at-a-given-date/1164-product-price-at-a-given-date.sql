-- 1. 2019-08-16 이전에 가격이 변경된 제품들의 최신 가격 찾기
WITH RankedPrices AS (
    SELECT
        product_id,
        new_price,
        change_date,
        -- 각 제품별로 change_date를 내림차순 정렬 (최신순)
        ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY change_date DESC) as rn
    FROM
        Products
    WHERE
        change_date <= '2019-08-16'
)
SELECT
    product_id,
    new_price AS price
FROM
    RankedPrices
WHERE
    rn = 1 -- rn = 1이 2019-08-16 기준 가장 최신 가격임

UNION

-- 2. 2019-08-16 이전에 가격 변경 기록이 없는 제품들 (기본값 10)
SELECT
    DISTINCT product_id,
    10 AS price
FROM
    Products
WHERE
    product_id NOT IN (
        -- 1번 그룹(이전에 변경된 제품)에 속한 ID는 제외
        SELECT product_id FROM RankedPrices
    );