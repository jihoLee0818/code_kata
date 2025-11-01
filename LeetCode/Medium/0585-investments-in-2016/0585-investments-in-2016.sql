WITH PolicyCounts AS (
    SELECT
        tiv_2016,
        -- 1. tiv_2015 값이 같은 행의 개수
        COUNT(*) OVER (PARTITION BY tiv_2015) AS tiv_2015_count,
        -- 2. (lat, lon) 값이 같은 행의 개수
        COUNT(*) OVER (PARTITION BY lat, lon) AS loc_count
    FROM
        Insurance
)
SELECT
    ROUND(SUM(tiv_2016), 2) AS tiv_2016
FROM
    PolicyCounts
WHERE
    tiv_2015_count > 1  -- 1. tiv_2015가 중복되고 (1보다 큼)
    AND loc_count = 1;  -- 2. 위치는 중복되지 않은 (1과 같음)