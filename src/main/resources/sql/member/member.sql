//성공 테이블.....
create table member(
	id varchar2(20),--유저아이디
	pwd varchar2(100), --유저패스워드
	name varchar2(30), --이름
	zip number(3), --우편번호
	address varchar2(300), --주소
	phone number(8), --휴대전화
	email varchar2(100),--이메일
	nickname varchar2(50),--별명
	act_nickname number(1), --별명으로활동
	role varchar2(20), --권한등급
	picture varchar2(300), --프로필사진
	indate DATE default sysdate,--등록날짜
	agree_location number(1),--위치정보 동의
	agree_promotion number(1)--마케팅 수신동의
);

//수정 12.13 id primary key 추가

create table member(
	id varchar2(20) primary key,--유저아이디
	pwd varchar2(3000), --유저패스워드
	name varchar2(30), --이름
	zip varchar2(10), --우편번호
	address varchar2(300), --주소
	phone varchar2(15), --휴대전화
	email varchar2(100),--이메일
	profile varchar2(300), --프로필사진
	role varchar2(20), --권한등급
	indate DATE default sysdate--등록날짜
);


---유저 롤
create table member_roles(
    role_idx number(11) not null,
    id varchar2(45) not null,
    role varchar2(45) not null,
    primary key(role_idx),
    constraint fk_id foreign key (id) references member(id)
    );

--sequense
CREATE SEQUENCE member_roles_seq
	start with 1
	increment by 1
	maxvalue 99999
    nocycle; 

--test role
insert into member_roles (role_idx, id, role) values (member_roles_seq.nextval, 'happysonday06', 'CLIENT');
insert into member_roles (role_idx, id, role) values (member_roles_seq.nextval, 'happysonday06', 'DISGNER');
insert into member_roles (role_idx, id, role) values (member_roles_seq.nextval, 'happysonday06', 'ADMIN');


--select role from member_roles where id= 'happysonday06'