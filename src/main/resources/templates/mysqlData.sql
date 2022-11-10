-- 데이터베이스 생성
create database dummy;

-- 게시글 테이블 생성
create table dummy.articlesEntity(
art_id int auto_increment primary key,
art_head varchar(10) not null ,
art_body varchar(4000) not null,
art_date varchar(10) not null,
art_hits varchar(10) not null,
art_pw varchar(20) default 1111 not null
) engine= InnoDB default character set = utf8mb4;

-- 댓글 테이블 생성
create table dummy.comments(
art_id int not null, 
com_id int auto_increment primary key,
com_body varchar(2000) not null, 
com_pw varchar(10),
FOREIGN KEY (`art_id`) REFERENCES `articlesEntity` (`art_id`)
) engine= InnoDB default character set = utf8mb4;