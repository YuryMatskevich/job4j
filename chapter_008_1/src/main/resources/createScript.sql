create schema if not exists test;

create table if not exists test.user (
  id_u int primary key auto_increment,
  name_u varchar(45) not null,
  role_u enum('ADMIN', 'USER') not null,
  id_cr int not null
);

create table if not exists test.credential (
  id_cr int primary key auto_increment,
  login_cr varchar(45) not null,
  passport_cr varchar(45) not null,
  unique (login_cr, passport_cr)
);

create table if not exists test.ad (
  id_a int primary key auto_increment,
  describe_a text not null,
  price_a double not null,
  state_c boolean not null default false,
  create_c bigint not null,
  picture_c blob default null,
  id_c int not null,
  id_u int not null
);

create table if not exists test.car (
  id_c int primary key auto_increment,
  age_c int not null,
  way_c int not null,
  sort_c enum('BMW', 'AUDI', 'VOLVO', 'RENO', 'KIA') not null,
  transmission_c enum('AT', 'MT', 'AMT') not null,
  manipulator_c enum('RIGHT', 'LEFT') not null,
  engine_c enum('DIESEL', 'PETROL', 'GAZ') not null,
  frame_c enum('HATCHBACK', 'SEDAN', 'CROSSOVER', 'COUPE', 'CONVERTIBLE') not null
);

insert into test.user (test.user.id_u, test.user.name_u, test.user.role_u, test.user.id_cr) value (1, 'ADMIN', 'ADMIN', 1);
insert into test.credential (test.credential.id_cr, test.credential.login_cr, test.credential.passport_cr) value (1, 'ADMIN', 'ADMIN');

alter table test.user add constraint fk_user_credential foreign key (id_cr) references test.credential (id_cr) on delete no action on update no action;
alter table test.user add unique user_unique_id_cr (id_cr);
alter table test.ad add constraint fk_ad_user foreign key (id_u) references test.user (id_u) on delete no action on update no action;
alter table test.ad add constraint fk_ad_car foreign key (id_c) references test.car (id_c) on delete no action on update no action;
alter table test.ad add unique ad_unique_id_c (id_c);