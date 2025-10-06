# Write your MySQL query statement below
SELECT
    ROUND(
        COUNT(T2.player_id) / COUNT(T1.player_id),
        2
    ) AS fraction
FROM
    (SELECT player_id, MIN(event_date) AS first_login FROM Activity GROUP BY player_id) AS T1
LEFT JOIN
    Activity AS T2 ON T1.player_id = T2.player_id AND T1.first_login = DATE_SUB(T2.event_date, INTERVAL 1 DAY);