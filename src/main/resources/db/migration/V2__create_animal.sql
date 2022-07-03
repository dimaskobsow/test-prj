create table animal_kind
(
    id   bigserial primary key,
    name varchar(50) unique
);

insert into animal_kind(name) values ('Кошка');
insert into animal_kind(name) values ('Собака');

create table animal
(
    id        bigserial primary key,
    name      varchar(50) unique not null ,
    birthdate date,
    sex       varchar(10),
    kind_id   bigint references animal_kind (id),
    owner_id  bigint references s_user (id)
);
