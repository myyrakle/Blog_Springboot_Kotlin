insert into CategoryGroup(group_name)
values
("Language"),
("Web-Frontend"),
("Web-Backend"),
("FrameWork"),
("Library"),
("LLVM"),
("LLVM-IR Doc");

insert into Category(category_name, its_group)
values
("Index", (select _id from CategoryGroup where group_name="LLVM-IR Doc")),
("시작", (select _id from CategoryGroup where group_name="LLVM-IR Doc")),
("고수준 구조", (select _id from CategoryGroup where group_name="LLVM-IR Doc")),
("함수 속성", (select _id from CategoryGroup where group_name="LLVM-IR Doc"));