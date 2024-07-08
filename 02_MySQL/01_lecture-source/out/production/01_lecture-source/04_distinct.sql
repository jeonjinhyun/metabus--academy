select
    distinct category_code
from
    tbl_menu
order by
    category_code;

select
    distinct ref_category_code
from
    tbl_category;

select distinct
    category_code
    ,orderable_status
    from tbl_menu;