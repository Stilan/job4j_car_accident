CREATE TABLE accident (
  id serial primary key,
  name varchar(2000),
  text varchar(2000),
  address varchar(2000),
  type_id serial references accident_type(id)
);

insert into accident(name) VALUES('dfdffddfs');



CREATE TABLE rules (
   id serial primary key,
   name varchar(2000)
);

CREATE TABLE  accident_type (
   id serial primary key,
   name varchar(2000)
);
drop table accident_type;
drop table accident;
drop table rules;
drop table accident_rules;

insert into rules(name) VALUES('Статья. 1');
insert into rules(name) VALUES('Статья. 2');
insert into rules(name) VALUES('Статья. 3');

insert into accident_type(name) VALUES('Две машины');
insert into accident_type(name) VALUES('Машина и человек');
insert into accident_type(name) VALUES('Машина и велосипед');


CREATE TABLE  accident_rules (
    id serial primary key,
    accident_id int references accident(id) NOT NULL,
    rule_id int references rules(id) NOT NULL
);

CREATE TABLE users (
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled boolean default true,
  PRIMARY KEY (username)
);

CREATE TABLE authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);

insert into accident_rules(accident_id, rule_id) VALUES(1, 1);
select * from accident_rules;
select * from accident;
select * from accident_type;