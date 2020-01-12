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
    password varchar(255) NOT NULL,
    its_role varchar(255) NOT NULL UNIQUE,
    foreign key(its_role) references Role(role_name)
) Engine=InnoDB;

insert into User(username, email, password, its_role)
value(
    "ADMIN",
    "sssang97@naver.com",
    "foobar",
    (select role_name from Role where role_name="ADMIN")
);

create table CategoryGroup (
    _id Int PRIMARY KEY Auto_Increment,
    group_name varchar(32) NOT NULL UNIQUE,
    visible boolean NOT NULL Default True,
    _position int
) Engine=InnoDB;

insert into CategoryGroup(group_name, _position) value("__null__", 255);

create table Category (
    _id Int PRIMARY KEY Auto_Increment,
    category_name varchar(32),
    visible boolean NOT NULL Default True,
    position int,
    post_count int NOT NULL DEFAULT 0,
    its_group int,
    foreign key(its_group) references CategoryGroup(_id)
) Engine=InnoDB;

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

