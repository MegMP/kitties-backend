--liquibase formatted sql
--changeset initial:1

drop table if exists `user_profile`;
drop table if exists `user`;
drop table if exists `friend`;
create table `user`
(
    user_id char(36) not null unique,
    username varchar(64) not null unique,
    password varchar(64) not null,

    primary key (user_id)
);

create table `user_profile`
(
    user_id char(36) not null unique,
    lastname varchar(64) not null,
    firstname varchar(64) not null,
    email   varchar(128) not null,
    city varchar(64) not null,

    foreign key (user_id) references user (user_id),
    constraint uq_profile unique (user_id)
);

create table `friend`
(
    user_id char(36)    not null,
    friend_id   char(36)  not null,

    foreign key (user_id) references user (user_id),
    foreign key (friend_id) references user (user_id),
    constraint uq_friends unique (user_id, friend_id)
)