create table user(
                 id int primary key auto_increment ,
                 username varchar(20) not null ,
                 email varchar(30) not null ,
                 address varchar(50),
                 password varchar(100) not null
                 )auto_increment = 1000;