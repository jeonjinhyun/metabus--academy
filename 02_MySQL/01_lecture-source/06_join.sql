select
    a.menu_code
    ,a.menu_name
    ,a.category_code
    ,b.category_name
    from tbl_menu as a
    join tbl_category as b
    on a.category_code = b.category_code;
