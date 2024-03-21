show tables;

create table hoewon (
	idx 		int not null auto_increment primary key,
	name		varchar(20) not null,
	age 		int default 20,
	gender	char(2) default '여자',
	address varchar(30)
);

desc hoewon;
drop table hoewon;

-- 필드에 자료가 무사히 들어가는지 확인하는 더미데이터
insert into hoewon values (default,'홍길동',default,'남자','서울');
insert into hoewon values (default,'김말숙',29,default,'청주');
insert into hoewon values (default,'이기자',33,'남자','제주');
insert into hoewon values (default,'소나무',41,'남자','서울');
insert into hoewon values (default,'오하늘',19,default,'청주');

select * from hoewon;

