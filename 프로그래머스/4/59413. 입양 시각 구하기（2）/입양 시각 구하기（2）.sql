with recursive

hours as (

select
    0 as hr

union all

select
    hr + 1
from
    hours
where
    hr < 23

),

xxx as (

select
    HOUR(DATETIME) as hr,
    count(1) as cnt
from
    ANIMAL_OUTS
group by
    hr

)

select
  hours.hr as HOUR,
  coalesce(xxx.cnt, 0) as COUNT
from
    hours
left join xxx using (hr)
order by
    hours.hr