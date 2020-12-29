------ 예약내역테이블
CREATE TABLE BOOKING (
    B_CODE NUMBER,    -- 예약코드
    ID VARCHAR2(50),        -- 아이디
    M_CODE VARCHAR2(30),    -- 메뉴코드
    D_CODE VARCHAR2(30),    -- 디자이너코드
    S_CODE VARCHAR2(30),    -- 매장코드
    B_DATE DATE,            -- 예약일정(년월일,요일)
    B_TIME DATE,            -- 예약일정(시간)
    B_BEAUTY_TIME NUMBER,      -- 시술 소요시간
    B_PRICE NUMBER,         -- 예약 금액
    B_STATUS CHAR(1) DEFAULT '0',       -- 예약 상태 / 0: 예약진행중, 1: 시술완료, 2: 취소(환불), 3: 삭제
    B_CANCEL_REASON VARCHAR2(20), -- 예약 취소 사유
    PRIMARY KEY (B_CODE)
    );

------ (12.29) 추가 (결제와 연동 코드)
   alter table BOOKING add MERCHANT_UID varchar2(50);
    
    -- 예약내역 시퀸스
    CREATE SEQUENCE BOOKING_SEQ
	START WITH 1
    INCREMENT BY 1
    MAXVALUE 99999
    NOCYCLE; 
    commit;

    
------ 메뉴테이블
CREATE TABLE SHOP_MENU (
    M_CODE VARCHAR2(30),    -- 메뉴코드
    S_CODE VARCHAR2(30),    -- 매장코드
    M_TYPE VARCHAR2(24),    -- 종분류 / 강아지 OR 고양이
    M_NAME VARCHAR2(50),    -- 메뉴명
    M_TIME NUMBER,            -- 소요시간
    M_PRICE NUMBER,         -- 금액
    M_INFO VARCHAR2(300),   -- 상품부가정보
    PRIMARY KEY (M_CODE),
    FOREIGN KEY (S_CODE) REFERENCES SHOP_INFO_TBL(S_CODE)
    );
---- 메뉴 사진테이블과 합침
alter table SHOP_MENU add M_P_PATH varchar2(500);
alter table SHOP_MENU add M_P_SYSNAME varchar2(100);
alter table SHOP_MENU add M_P_ORGNAME varchar2(100);


------ 메뉴옵션테이블
CREATE TABLE SHOP_MENU_OPTION (
    M_CODE VARCHAR2(30), -- 메뉴코드
    S_CODE VARCHAR2(30), -- 매장코드
    O_NAME VARCHAR2(30), -- 옵션명
    O_TIME NUMBER,         -- 추가 소요시간
    O_PRICE NUMBER       -- 추가 금액
    );

(폐기예정)------ 메뉴사진테이블 
CREATE TABLE SHOP_MENU_PHOTO (
    M_CODE VARCHAR2(30),                -- 메뉴코드
    S_CODE VARCHAR2(30),                -- 매장코드
    M_P_PATH VARCHAR2(500) NOT NULL,    -- 사진파일경로
    M_P_SYSNAME VARCHAR2(100) NOT NULL, -- 사진파일시스템이름
    M_P_ORGNAME VARCHAR2(100) NOT NULL, -- 사진파일원본이름
    FOREIGN KEY (M_CODE) REFERENCES SHOP_MENU(M_CODE) ON DELETE CASCADE,
    FOREIGN KEY (S_CODE) REFERENCES SHOP_INFO_TBL(S_CODE) ON DELETE CASCADE
    );
=======================================================


------ 예약_VIEW 테이블
CREATE OR REPLACE VIEW booking_view AS
SELECT 
	b.b_code, b.id, b.b_price, b.b_status, b.b_beauty_time, b.merchant_uid,
	m.m_code, m.m_name, m.m_p_path, m.m_p_sysname, m.m_p_orgname,
	s.s_code, s.s_name,
	d.d_code, d.d_name,
	b.b_date, b.b_time
FROM booking b, 
	shop_menu m, 
	shop_info_tbl s, 
	shop_designer_tbl d
WHERE 
	b.m_code = m.m_code AND
	b.s_code = s.s_code AND
	b.d_code = d.d_code;
commit;