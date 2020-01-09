create table Role (
    _id Int PRIMARY KEY Auto_Increment,
    role_name varchar(255) NOT NULL,
    power int NOT NULL,
    constraint power_constraint check(0<=power and power<=10)
) Engine=InnoDB;

insert into Role(role_name, power)
values
("ADMIN", 10),
("MEMBER", 5);

create table User (
    _id Int PRIMARY KEY Auto_Increment,
    username varchar(255) NOT NULL,
    foreign key(role_name) references(_id)
) Engine=InnoDB;

create table Post (
    _id Int PRIMARY KEY Auto_Increment,
    time DateTime NOT NULL,
    title varchar(255) NOT NULL,
    body varchar(100000) NOT NULL
) Engine=InnoDB;