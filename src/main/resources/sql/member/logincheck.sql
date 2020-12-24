create table accesss tbl(
	email varchar2(50),
	id varchar2(50),
	password varchar2(50),
	authority varchar2(50) default 'ROLE_MEMBER',
	enabled number(1) default 1,
	passwordExpired number(1) default 1,
	locked number(1) default 1,
	indate default to_char(sysdate,'yyyy/mm/dd hh24:mi:ss')
)


create table login_check_tbl 
	(
	email varchar2(100) primary key, 
	login_fail_count number(1) default 0, 
	locked number(1) default 1, --1:no Lock 0: Lock
	latest_try_date varchar2(20) default to_char(sysdate,'yyyy/mm/dd hh24:mi:ss'),
	--locked_date varchar2(20)--이건 굳이 필요하지 않을듯...
	);


*다시 있어야할 정보들
access_check_tbl (로그인 및 접근제한에 대한 정보)

id
email
password
latest_login_date
fail_count
--status(이거 있어야된다) 이것또한 유니크한 값이니깐 중복이 회원정보에 넣자 최종 결과의 데이터값이니깐
changed_pwd_date
two_weeks_date--이주간 알림표시 제한(체크한 시점을 받아서 update 오라클 연산)
--recovery_date--disabled로부터 해제된날 -> 이거는 

=========================================================================================
회원가입 후 최초 로그인시
insert into login_check_tbl (email, login_fail_count) values ('mrson1106', 1);


실패시 카운트 계속 추가
update login_check_tbl set login_fail_count=login_fail_count+1, latest_try_date = to_char(sysdate,'yyyy/mm/dd hh24:mi:ss') where email = 'mrson1106';
select * from login_check_tbl;

--->임의로 비밀번호 적용되도록 업데이트

5회일시 locked 적용
update login_check_tbl set login_fail_count=login_fail_count+1, locked=0, latest_try_date = to_char(sysdate,'yyyy/mm/dd hh24:mi:ss'), locked_date=to_char(sysdate,'yyyy/mm/dd hh24:mi:ss') where email = 'mrson1106';
select * from login_check_tbl;


중간에 맞출경우 초기화
=========================================================================================

