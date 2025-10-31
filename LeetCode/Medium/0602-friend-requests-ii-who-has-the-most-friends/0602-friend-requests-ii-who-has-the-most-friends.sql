SELECT
    id,
    COUNT(*) AS num
FROM
    (
        -- 1. requester_id 목록
        SELECT requester_id AS id FROM RequestAccepted
        
        UNION ALL
        
        -- 2. accepter_id 목록
        SELECT accepter_id AS id FROM RequestAccepted
    ) AS AllFriends
GROUP BY
    id
ORDER BY
    num DESC
LIMIT 1;