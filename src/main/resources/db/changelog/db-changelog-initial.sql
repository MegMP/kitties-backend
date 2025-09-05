create table `users`
(
    user_id varchar(256) not null unique
    lastname varchar(64) not null,
    firstname varchar(64) not null,
    email   varchar(128) not null,
    username varchar(64) not null unique,
    password varchar(64) not null,
    city varchar(64) not null

    primary key (user_id)
)

create table `friends`
(
    user_id varchar(256) not null,
    friend_id varchar(256) not null,

    foreign key (user_id) references users (user_id),
    foreign key (friend_id) references users (user_id),
    constraint uq_friends unique (user_id, friend_id)
)