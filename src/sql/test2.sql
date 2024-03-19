show tables;

create table insarok (
	idx 		int not null auto_increment primary key, /* 고유번호 */
	buser 	varchar(10) not null,			/* 부서명 */
	name 		varchar(20) not null,			/* 부서원(성명) */
	jikwi 	varchar(10) not null default '사원',		/* 직위 */
	gender 	char(2) not null default '남자',		/* 성별 */
	age 		int default 25, 			/* 나이 */
	ipsail 	datetime not null default now(), /* 입사일(기본값: 오늘날짜) (함수는 항상괄호와) */ /*날짜타입: date, 시간타입: time */
	address varchar(50)				/* 주소(공백 허용) */
	/* primary key(idx) */ /* 이렇게 입력할 수도 있음 */
);

desc insarok;
-- drop table insarok; /* 비상시를 대비해 주석처리했다가 지울때만 블록하여 사용 */

insert into insarok values (default, '인사과','홍길동','과장',default,38,'1995-1-5','서울');
insert into insarok values (default, '총무과','김말숙','부장',default,45,'1991-11-5','청주');
insert into insarok values (default, '영업과','이기자','사원',default,25,'2015-4-15','부산');
insert into insarok values (default, '자재과','소나무','대리',default,30,'2012-1-13','제주');
insert into insarok values (default, '자재과','강감찬','사원',default,27,'2015-5-25','청주');
insert into insarok values (default, '총무과','오하늘','사원',default,28,'2016-7-16','서울');
insert into insarok values (default, '인사과','김효진','대리',default,33,'2013-12-4','서울');
insert into insarok values (default, '영업과','김기자','대리',default,32,'2012-3-2','부산');
insert into insarok values (default, '인사과','이순신','부장',default,50,'1992-1-23','청주');
insert into insarok values (default, '인사과','고인돌','사원',default,26,'2017-6-30','인천');
insert into insarok values (default, '영업과','나도야','대리',default,32,'2013-4-29','서울');
insert into insarok values (default, '영업과','홍길순','대리',default,33,'2014-5-2','인천');
insert into insarok values (default, '영업과','가나다','사원',default,28,'2014-7-9','청주');
insert into insarok values (default, '자재과','심재영','부장',default,51,'2000-8-16','청주');
insert into insarok values (default, '총무과','대나무','대리',default,35,'2017-9-20','부산');
insert into insarok values (default, '총무과','권문선','과장',default,40,'1996-9-26','서울');
insert into insarok values (default, '영업과','강서현','부장',default,49,'2001-3-3','제주');
insert into insarok values (default, '영업과','이시언','사원',default,26,'2016-2-18','부산');
insert into insarok values (default, '인사과','손흥민','사원',default,25,'2016-6-22','청주');
insert into insarok values (default, '총무과','김연아','사원',default,27,'2013-3-26','인천');
insert into insarok values (default, '자재과','홍길동','과장',default,40,'2001-10-19','청주');

select * from insarok;

-- insarok 테이블의 성명/직위/주소 필드만 모든 자료 표시?
select name,jikwi,address from insarok;

-- 홍길동 레코드만 출력
select * from insarok where name='홍길동';

-- '서울'에 사는 '홍길동'레코드만 출력?
select * from insarok where address='서울' and name='홍길동';

-- 홍길동 사원만 출력?
select * from insarok where jikwi='사원' and name='홍길동';

-- 서울에 사는 모든 사람 출력
select * from insarok where address='서울';

-- 입사년도가 2000년 이전에 입사한 직원을 보여주시오?
select * from insarok where ipsail < '2000-1-1';

-- 서울에 살지 않는 직원?
select * from insarok where address != '서울';
select * from insarok where address <> '서울'; /* 두가지 다 not을 의미 */

-- 입사년도가 2000년~2010년 이전에 입사한 직원을 보여주시오?
select * from insarok where ipsail >= '2000-1-1' and ipsail <='2010-12-31';
-- 앞의 범위 연산자 대신에 between~and 변경 가능
select * from insarok where ipsail between '2000-1-1' and '2010-12-31';

-- 30대 회사원 출력?
select * from insarok where age >= 30 and age <= 39;
select * from insarok where age between 30 and 39;

-- 서울/부산 에 사는 직원?
select * from insarok where address='서울' or address='부산';
-- 앞의 or 연산자는 in()으로 변경 가능
select * from insarok where address in('서울','부산');

-- 서울/부산에 사는 사원만 출력?
select * from insarok where jikwi='사원' and address in('서울','부산');

-- '김'씨만 출력?
select * from insarok where name like '김%';

-- '나무'로 끝나는 이름을 가진 직원 출력?
select * from insarok where name like '%나무';

-- '이시언'을 '이재혁'으로 이름 변경?
update insarok set name='이재혁' where name='이시언';
update insarok set name='가재다' where name='가나다';
update insarok set name='재서현' where name='강서현';

-- 이름 중에서 '재'라는 글자를 포함한 직원의 직급을 '과장'으로 변경하시오?
select * from insarok where name like '%재%';
update insarok set jikwi='과장' where name like '%재%';

-- 이름 중 2번째 글자가 '나'인 직원은?
select * from insarok where name like '_나%';
/* 밑줄 한개당 글자 하나 */

-- 이름 중에서 '재'라는 글자를 포함한 직원 중에서 '청주'에 사는 직원의 이름과 입사일과 주소?
select name,ipsail,address from insarok where name like '%재%' and address='청주';

-- 이름 중에서 '재'라는 글자를 포함한 직원 중에서 '청주'에 사는 직원 중 나이가 40이상인 직원을 퇴사시키시오?
select * from insarok where name like '%재%' and address='청주';
delete from insarok where name like '%재%' and address='청주' and age >= 40;

-- '홍길순' 성별을 '여자'로 변경?
update insarok set gender='여자' where name='홍길순';

-- 이름 오름차순으로 출력? (순서 : order by ~~ / 오름차순: asc(생략가능), 내림차순: desc)
select * from insarok order by name;
select * from insarok order by name desc;

-- 남자인 자료 중에서 나이 오름차순으로 출력?
select * from insarok where gender='남자' order by age;

-- 남자인 자료 중에서 나이 오름차순으로, 같은 나이면 입사일 내림차순 출력?
select * from insarok where gender='남자' order by age, ipsail desc;

-- 전체자료 중에서 5명만 출력하시오 (입력순서 중 앞에서 5개)
select * from insarok limit 5;

-- 뒤에서 5명(나중에 입력한 회원)만 출력?
select * from insarok order by idx desc limit 5;

-- 남자 회원 5명만 나이 내림차순으로 보여주시오.
select * from insarok where gender='남자' order by age desc limit 5;

-- 남자 회원 중에서 앞에서 2명을 빼고, 5명만 출력하시오? (limit 인덱스 번호, 출력개수)
select * from insarok where gender='남자' order by idx limit 2,5;

