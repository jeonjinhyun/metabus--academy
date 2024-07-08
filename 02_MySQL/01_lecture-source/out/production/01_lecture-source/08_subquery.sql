select *
    from tbl_menu
    where category_code=(
                            select category_code
                            from tbl_menu
                            where menu_name='민트미역국'
                        );