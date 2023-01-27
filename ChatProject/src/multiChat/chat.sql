drop table UserContent;

create table UserContent(
    ID varchar2(20) PRIMARY KEY,
    PW varchar2(40) NOT NULL,
    NAME varchar2(40) NOT NULL
);

select * from UserContent;

insert into UserContent
values ('hong', '1234', '홍길동');

insert into UserContent
values ('kim', '1234', '김유신');

insert into UserContent
values ('lee', '1234', '이순신');

insert into UserContent
values ('choi', '1234', '최강창민');

commit;

--======================================================

drop table ChatContents;

create table ChatContents (
    UserName varchar2(40) NOT NULL,
    Contents varchar2(1000) NOT NULL,
   -- chatTime TIMESTAMP default systimestamp
   chatTime date default sysdate
);
-- 채팅내용 저장하기 위해 테이블 생성
-- 자바에서 insert로 순서대로 입력해도 꼭 그 순서로 조회되지 않기 때문에
-- 채팅시간순으로 조회 가능하게 하기 위해 시간변수 설정

ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD HH24:MI:SS';
-- date 형식을 'YYYY-MM-DD HH24:MI:SS' 형식으로 맞춰주기

delete from ChatContents;

select * from ChatContents order by 3;
-- 먼저 입력된 채팅시간순으로 조회하기 위해 order by 3 함

commit;