show tables;

create table test(
	idx int not null auto_increment primary key,	-- 고유번호
	name varchar(20) not null,				-- 성명
	age int default 20,								-- 나이
	gender varchar(2) default '남자',	-- 성별
	job varchar(20) default '기타',    -- 직업
	address varchar(50)								-- 주소
);

-- 구조까지 다 지우기
drop table test;
-- 레코드만 지우기
delete from test;
desc test;

insert into test values (default,'홍길동',default,default,default,'서울');
insert into test values (default,'김말숙',34,'여자',default,'청주');
insert into test values (default,'이기자',29,'남자',default,'부산');
insert into test values (default,'김연아',default,'여자',default,'제주');
insert into test values (default,'손흥민',33,default,default,'서울');

-- 마지막은 auto_increment 대신에 default
insert into test values (default,'소나무',55,default,default,'제주');
insert into test (name,age,gender,address,job,idx) values ('대나무',11,'여자','제주','학생',default);
insert into test values (default,'감나무',22,'남자',default,'서울');
select * from test;

delete from test where name='손흥민';

/* 레코드 수정하기 : update 테이블명 set 필드명='수정내용' where '조건(필드명=값)'; */

update test set age=25 where name='홍길동';

-- 남자들의 나이를 1살씩 모두 더해주시오.
update test set age = age+1;
update test set age = age-1;
update test set age = age+1 where gender='남자';

-- 서울에 사는 사람들만 보여주시오.
select * from test where address='서울';
select * from test where address='서울' or address='부산';

-- 나이가 30살 미만인 회원을 보여주시오.
select * from test where age<30;

-- 나이가 30살 미만인 여자 회원을 보여주시오.
select * from test where age<30 and gender='여자';

-- 청주에 사는 회원 확인?
select * from test where address='청주';
-- 앞부분 select * from test 만 블록후 확인해도 기능함

-- 청주에 사는 회원 삭제?
DELETE FROM test WHERE address='청주';

-- '청주/남자/19/강감찬' 인 회원을 등록하세요?
INSERT INTO test VALUES ('강감찬',19,default,'청주');

-- 서울에 사는 여자 회원의 나이를 2살씩 빼주시오?
update test set age = age-2 where gender='여자' and addresss='서울';

-- test테이블의 구조 보기
desc test;

-- 필드 구조변경...(alter table 테이블명 ~~)
-- test테이블에 job필드(컬럼)(직업명은 5글자이내, 기본값: 기타)를 추가(add column)하시오
alter table test add column job varchar(6) default '기타';

-- test 테이블에 job필드 삭제하기 (drop column)
alter table test drop column job;

-- test테이블의 job필드의 길이를 20자로 수정하시오(modify column) - 속성 변경
alter table test modify column job varchar(20);

-- test 테이블의 name필드명을 irum필드로 변경하시오.(change column) - 필드명(컬럼이름) 변경
alter table test change column name irum varchar(20);
alter table test change column irum name varchar(20);

-- test테이블에 고유번호(idx) 필드를 추가하시오. - 기본키(구분이 될 수 있는 중복을 배제한 필드) 추가
alter table test add column idx int not null auto_increment primary key;
/* auto_increment: 자동으로 숫자 증가(중복을 배제하는 기본 숫자) => 무조건 primary key로 지정이 되어있어야 함
	 한번이라도 사용한 숫자는 다시 사용하지 않음(default사용시), 필드에 없는 값을 지정하면 그 번호로 들어감 */

/* 하나의 테이블에 primary key가 여러 개 올 수 있음
 	 처음에 primary key를 사용하고 나서 중복을 제외하는 다른 필드를 주고 싶을 땐 unique key 사용
 	 (primary key 여러 번 사용은 가능하지만 primarykey는 필드가 다를 때 주로 쓰임 - 청주에 사는 홍길동 으로 지역과 이름 둘다 묶어 중복금지할 경우) */

-- idx=5번 삭제하시오.
delete from test where idx=5;
-- idx=7번 삭제하시오.
delete from test where idx=7;

-- 고유번호(idx) 값을 5번부터 시작하도록 설정하시오?
alter table test auto_increment = 5; -- idx 초기화 할 때 필요