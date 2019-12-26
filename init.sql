create database MyBlog

create table Posts (
    _id Int PRIMARY KEY Auto_Increment,
    time DateTime NOT NULL,
    title varchar(100),
    body varchar(100000)
)