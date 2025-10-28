SELECT
    CASE
        -- 1. 짝수 ID인 경우: id - 1
        WHEN id % 2 = 0 THEN id - 1
        
        -- 2. ID가 총 학생 수와 같고 홀수인 경우 (마지막 학생): id
        WHEN id = (SELECT COUNT(*) FROM Seat) THEN id
        
        -- 3. 그 외 모든 홀수 ID인 경우: id + 1
        ELSE id + 1
    END AS id,
    student
FROM
    Seat
ORDER BY
    id ASC;