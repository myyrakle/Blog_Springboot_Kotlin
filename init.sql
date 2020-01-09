create table Role (
    _id Int PRIMARY KEY Auto_Increment,
    role_name varchar(255) NOT NULL UNIQUE,
    power int NOT NULL,
    constraint power_constraint check(0<=power and power<=10)
) Engine=InnoDB;

insert into Role(role_name, power)
values
("ADMIN", 10),
("MEMBER", 5);

create table User (
    _id Int PRIMARY KEY Auto_Increment,
    username varchar(255) NOT NULL UNIQUE,
    email varchar(255) NOT NULL UNIQUE,
    its_role varchar(255) NOT NULL UNIQUE,
    foreign key(its_role) references Role(role_name)
) Engine=InnoDB;

insert into User(username, email, its_role)
value(
    "ADMIN",
    "sssang97@naver.com",
    (select role_name from Role where role_name="ADMIN")
);

create table CategoryGroup (
    _id Int PRIMARY KEY Auto_Increment,
    group_name varchar(32) NOT NULL UNIQUE,
    visible boolean NOT NULL Default True,
    _position int
) Engine=InnoDB;

insert into CategoryGroup(group_name) value("__null__");

create table Category (
    _id Int PRIMARY KEY Auto_Increment,
    category_name varchar(32),
    visible boolean NOT NULL Default True,
    position int,
    its_group int,
    foreign key(its_group) references CategoryGroup(_id)
) Engine=InnoDB;

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

create table Post (
    _id Int PRIMARY KEY Auto_Increment,
    time DateTime NOT NULL,
    title varchar(255) NOT NULL,
    body varchar(10000) NOT NULL,
    writer_id int NOT NULL,
    foreign key(writer_id) references User(_id),
    category_id int NOT NULL,
    foreign key(category_id) references Category(_id)
) Engine=InnoDB;

insert into Post(_id, time, title, body, writer_id, category_id)
values(

)

