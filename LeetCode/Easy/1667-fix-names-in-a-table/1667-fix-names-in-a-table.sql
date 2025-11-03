SELECT
    user_id,
    CONCAT(
        UPPER(SUBSTRING(name, 1, 1)), -- 첫 글자를 대문자로
        LOWER(SUBSTRING(name, 2))      -- 나머지 글자를 소문자로
    ) AS name
FROM
    Users
ORDER BY
    user_id;