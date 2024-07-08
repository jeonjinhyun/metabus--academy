select  category_code,sum(menu_price) as "sum", avg(menu_price) as "avg",count(menu_price) as "count", min(menu_price) as "min",max(menu_price) as "max"
    from tbl_menu
    group by category_code;

select menu_price,category_code
from tbl_menu
group by
    menu_price, category_code
having category_code between 5 and 8;

select category_code,sum(menu_price) as '합계금액'
from tbl_menu
group by category_code
having sum(menu_price)>=30000;

select category_code,sum(menu_price)
from tbl_menu
group by category_code
order by sum(menu_price) desc
limit 1;

