-- 쿼리 1: 가장 많은 평점을 남긴 사용자
(SELECT
    u.name AS results
FROM
    MovieRating r
JOIN
    Users u ON r.user_id = u.user_id
GROUP BY
    r.user_id
ORDER BY
    COUNT(r.movie_id) DESC, u.name ASC
LIMIT 1)

UNION ALL

-- 쿼리 2: 2020년 2월의 최고 평균 평점 영화
(SELECT
    m.title AS results
FROM
    MovieRating r
JOIN
    Movies m ON r.movie_id = m.movie_id
WHERE
    DATE_FORMAT(r.created_at, '%Y-%m') = '2020-02'
GROUP BY
    r.movie_id
ORDER BY
    AVG(r.rating) DESC, m.title ASC
LIMIT 1);