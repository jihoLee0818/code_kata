with car as (
    select CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where START_DATE between '2022-08-01' and '2022-10-31'
    group by CAR_ID
    having count(*) >= 5
)

select month(START_DATE) as MONTH, CAR_ID, count(*) as RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where START_DATE between '2022-08-01' and '2022-10-31' and CAR_ID in (select CAR_ID from car)
group by month(START_DATE), CAR_ID
order by MONTH asc, CAR_ID desc;