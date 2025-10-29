-- 1. 일별 합계 계산
WITH DailySales AS (
    SELECT
        visited_on,
        SUM(amount) AS daily_amount
    FROM
        Customer
    GROUP BY
        visited_on
),
-- 2. 이동 평균 계산
SalesWithWindow AS (
    SELECT
        visited_on,
        -- 현재 행을 포함해 6일 전(총 7일)의 합계
        SUM(daily_amount) OVER (
            ORDER BY visited_on 
            ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
        ) AS amount,
        -- 현재 행을 포함해 6일 전(총 7일)의 평균
        AVG(daily_amount) OVER (
            ORDER BY visited_on 
            ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
        ) AS avg_amount,
        -- 필터링을 위한 첫 방문일 계산
        MIN(visited_on) OVER () AS first_visit_date
    FROM
        DailySales
)
-- 3. 최종 필터링 및 반올림
SELECT
    visited_on,
    amount,
    ROUND(avg_amount, 2) AS average_amount
FROM
    SalesWithWindow
WHERE
    -- 7일치가 쌓인 첫날 (첫 방문일 + 6일)부터 출력
    visited_on >= DATE_ADD(first_visit_date, INTERVAL 6 DAY)
ORDER BY
    visited_on;