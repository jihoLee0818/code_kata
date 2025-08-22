-- 코드를 입력하세요
SELECT * 
FROM REST_INFO;

-- 올바른 해결법: 단일행 서브쿼리 사용
SELECT FOOD_TYPE
     , REST_ID
     , REST_NAME
     , FAVORITES
FROM REST_INFO
WHERE (FOOD_TYPE, FAVORITES) IN (SELECT FOOD_TYPE, MAX(FAVORITES)
                                 FROM REST_INFO
                                 GROUP BY FOOD_TYPE)
ORDER BY 1 DESC;