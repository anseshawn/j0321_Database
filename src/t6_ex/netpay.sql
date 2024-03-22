show tables;

-- 직급별 본봉 테이블
create table salary (
	jikkub char(8) not null primary key,		/* 직급 */
	bonbong int not null										/* 본봉 */
);
desc salary;
insert into salary values ('부장',5000000);
insert into salary values ('과장',4000000);
insert into salary values ('대리',3000000);
insert into salary values ('사원',2000000);
select * from salary;

-- 인사관리 테이블
create table insa (
	idx int not null auto_increment primary key,	/* 인사관리 고유번호 */
	sabun char(8) not null,					/* 직급코드(년(2)월(2)일(2)일련번호(2)) */
	buseo varchar(10) not null,			/* 인사과/총무과/생산과/영업과 */
	name varchar(20) not null,			/* 성명 */
	jikkub char(8) not null,		/* 직급: 부장/과장/대리/사원 */
	age int default 25,							/* 나이, 기본값 25 */
	ipsail datetime default now(),	/* 입사일 */
	gender char(2) default '여자',		/* 성별 */
	address varchar(30),						/* 주소 */
	unique key (sabun),								/* 중복불가키: 사번 */
	foreign key (jikkub) references salary (jikkub)	/* 외래키 */
);
desc insa;
drop table insa;
insert into insa values (default, '24032101','인사과','홍길동','과장',35,'2000-1-5','남자','서울');
insert into insa values (default, '24032102','영업과','김말숙','대리',31,'2007-11-25',default,'청주');
insert into insa values (default, '24032201','총무과','이기자','사원',25,'2022-8-22','남자','서울');
select * from insa;


-- 1. 이기자의 급여?
-- 1-1. 사원의 본봉?
select * from salary where jikkub = '사원';
-- 2. '이기자'의 본봉?
select * from salary s, insa i where s.jikkub = i.jikkub;
/* salary의 jikkub과 insa의 jikkub이 같은 경우의 테이블 출력, 두 테이블의 키가 중복되면 에러가 발생한다. */
select i.name,i.jikkub,s.bonbong from salary s, insa i where s.jikkub = i.jikkub;

-- vo를 따로 만들어도 되지만 insa(필드가 많은 테이블) VO로 통일해서 만드는게 편할듯...
-- 만약 필드 이름이 같다면 i.name as irum << 별명을 붙여서 출력, irum을 VO에 넣기