create table s_user
(
    id            bigserial primary key,
    login         varchar(100) unique not null,
    password_hash varchar(60)         not null,
    name          varchar(50)         not null,
    last_fail     timestamp,
    login_attempt int  default 0,
    blocked       bool default false
);
