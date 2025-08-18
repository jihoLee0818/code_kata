SELECT USER_ID, NICKNAME, sum(PRICE) as TOTAL_SALES
from (
    select uu.USER_ID,ub.PRICE,ub.STATUS,uu.NICKNAME
    from USED_GOODS_BOARD as ub
    join USED_GOODS_USER as uu on ub.WRITER_ID = uu.USER_ID
    where ub.STATUS = 'done'
) as user_i
group by USER_ID
having sum(PRICE) >= '700000'
order by sum(PRICE) asc;