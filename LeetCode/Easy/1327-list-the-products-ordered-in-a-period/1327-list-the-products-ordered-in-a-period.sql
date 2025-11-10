SELECT
    p.product_name,
    SUM(o.unit) AS unit
FROM
    Products p
JOIN
    Orders o ON p.product_id = o.product_id
WHERE
    -- 2020년 2월 데이터만 필터링
    DATE_FORMAT(o.order_date, '%Y-%m') = '2020-02'
GROUP BY
    p.product_id, p.product_name
HAVING
    -- 총 주문 수량이 100 이상인 것만
    SUM(o.unit) >= 100;