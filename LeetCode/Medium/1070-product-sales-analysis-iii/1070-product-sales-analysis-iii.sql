WITH RankedSales AS (
    SELECT
        product_id,
        year,
        quantity,
        price,
        RANK() OVER (PARTITION BY product_id ORDER BY year ASC) as rnk
    FROM
        Sales
)
SELECT
    product_id,
    year AS first_year,
    quantity,
    price
FROM
    RankedSales
WHERE
    rnk = 1;