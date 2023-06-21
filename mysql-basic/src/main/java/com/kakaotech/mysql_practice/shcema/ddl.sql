create table Member
(
    id int auto_increment,
    email varchar(20) not null,
    nickname varchar(20) not null,
    birthday date not null,
    createdAt datetime not null,
    constraint member_id_uindex
        primary key (id)
);

create table MemberNicknameHistory
(
    id int auto_increment,
    memberId int not null,
    nickname varchar(20) not null,
    createdAt datetime not null,
    constraint memberNicknameHistory_id_uindex
        primary key (id)
);

create table Follow
(
    id int auto_increment,
    fromMemberId int not null,
    toMemberId int not null,
    createdAt datetime not null,
    constraint Follow_id_uindex
        primary key (id)
);

create unique index Follow_fromMemberId_toMemberId_uindex
    on Follow (fromMemberId, toMemberId);


create table POST
(
    id int auto_increment,
    memberId int not null,
    contents varchar(100) not null,
    createdDate date not null,
    createdAt datetime not null,
    constraint POST_id_uindex
        primary key (id)
);

create index POST__index_member_id
    on POST (memberId);

create index POST__index_created_date
    on POST (createdDate);

create index POST__index_member_id_created_date
    on POST (memberId, createdDate);

select count(*)
from POST;

explain SELECT memberId, createdDate, count(id)
FROM POST
WHERE memberId = 6 and createdDate between '2000-01-01' and '2023-12-31'
GROUP BY memberId, createdDate;

explain SELECT memberId, createdDate, count(id)
FROM POST use index (POST__index_member_id)
WHERE memberId = 6 and createdDate between '2000-01-01' and '2023-12-31'
GROUP BY memberId, createdDate;

select *
from POST
where memberId = 6
limit 2
offset 2;

create table Timeline
(
    id int auto_increment,
    memberId int not null,
    postId int not null,
    createdAt datetime not null,
    constraint Timeline_id_uindex
        primary key (id)
    # 인덱스 추가해서 성능 테스트 진행해보자
);

alter table POST add column likeCount int;

alter table POST add column version int default 0;

create table PostLike
(
    id int auto_increment,
    memberId int not null,
    postId int not null,
    createdAt datetime not null,
    constraint PostLike_id_uindex
        primary key (id)
);

alter table Member add column userId varchar(20) not null;

alter table Member add column userPassword varchar(20) not null;

truncate Timeline;

create table Comment
(
    id int auto_increment,
    postId int not null,
    commentId int,
    contents varchar(200) not null,
    createdAt datetime not null,
    constraint Comment_id_uindex
        primary key (id)
);

alter table Comment add column memberId int not null;