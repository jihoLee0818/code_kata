SELECT
    person_name
FROM (
    SELECT
        person_name,
        SUM(weight) OVER (ORDER BY turn) AS cumulative_weight
    FROM
        Queue
) AS SubQuery
WHERE
    cumulative_weight <= 1000
ORDER BY
    cumulative_weight DESC
LIMIT 1;