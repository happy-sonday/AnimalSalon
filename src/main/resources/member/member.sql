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