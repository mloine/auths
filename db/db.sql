CREATE TABLE user(
  id BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(60),
  pwd VARCHAR (60)
);

insert into user(name,pwd) values("admin","123456");

select * from user;