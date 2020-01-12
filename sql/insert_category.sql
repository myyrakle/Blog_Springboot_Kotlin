insert into CategoryGroup(group_name, _position)
values
("Language", 1),
("Web-Frontend", 2),
("Web-Backend", 3),
("FrameWork", 4),
("Library", 5),
("LLVM", 6),
("LLVM-IR Doc", 7);

insert into Category(category_name, its_group)
values
("Index", (select _id from CategoryGroup where group_name="LLVM-IR Doc")),
("시작", (select _id from CategoryGroup where group_name="LLVM-IR Doc")),
("고수준 구조", (select _id from CategoryGroup where group_name="LLVM-IR Doc")),
("함수 속성", (select _id from CategoryGroup where group_name="LLVM-IR Doc"));