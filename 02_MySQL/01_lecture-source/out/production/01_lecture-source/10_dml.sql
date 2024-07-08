insert into tbl_menu
values (null, '바나나해장국',8500,4,'Y');

select * from tbl_menu;

insert into tbl_menu(menu_name,menu_price,category_code,orderable_status)
values ('초콜릭죽',6500,7,'Y');

insert into tbl_menu(orderable_status,menu_price,menu_name,category_code)
values ('Y',5500,'파인애플탕',4);

insert into tbl_menu
values (null,'참치맛아이스크림',1700,12,'Y'),
       (null,'멸치맛아이스크림',1500,11,'Y'),
       (null,'소시지맛커피',2500,8,'Y');

select menu_code,category_code
from tbl_menu
where menu_name = '파인애플탕';

update
    tbl_menu
set
    category_code = 7
where
    menu_code = 24;

update tbl_menu
set category_code=6
where menu_code = (select tmp.menu_code
                   from (select menu_code
                         from tbl_menu
                         where menu_name='파인애플탕'
                         ) tmp
                   );
# update나 delete 시 자기 자신 테이블의 데이터를 사용 시 1093 에러가 발생한다.

delete from tbl_menu
where menu_code = 24;

SELECT
    ELT(2, '사과', '딸기', '바나나'), FIELD('딸기', '사과', '딸기', '바나나'),
    FIND_IN_SET('바나나', '사과,딸기,바나나'), INSTR('사과딸기바나나', '딸기'), LOCATE('딸기', '사과딸기바나나');