-- liquibase formatted sql

-- changeset zenox:20220724

drop table if exists user;
create table if not exists user (
    id integer auto_increment primary key,
    name varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    phone varchar(255) null,
    avatar_url varchar(255) null,
    gender varchar(255) null,
    age integer null,
    city varchar(255) null,
    fields json null
);

drop table if exists role;
create table if not exists role (
    id integer auto_increment primary key,
    name varchar(255) not null
);

drop table if exists user_role;
create table if not exists user_role (
    user_id integer not null,
    role_id integer not null,
    constraint user_role_pkey primary key(user_id, role_id),
    constraint user_id_fk foreign key(user_id) references user(id),
    constraint role_fk foreign key(role_id) references role(id)
);

drop table if exists tag;
create table if not exists tag (
    id integer auto_increment primary key,
    name varchar(255) not null
);

drop table if exists post;
create table if not exists post (
    id integer auto_increment primary key,
    creator_id integer not null,
    title varchar(255) not null,
    description varchar(255) null,
    tags json not null,
    last_edit_time timestamp not null,
    status varchar(255) not null,
    constraint post_user_fk foreign key(creator_id) references user(id)
);

drop table if exists reply;
create table if not exists reply (
    id integer auto_increment primary key,
    post_id integer not null,
    content varchar(255) not null,
    replier_id integer not null,
    reply_time timestamp not null,
    constraint reply_post_fk foreign key(post_id) references post(id),
    constraint reply_user_fk foreign key(replier_id) references user(id)
);


