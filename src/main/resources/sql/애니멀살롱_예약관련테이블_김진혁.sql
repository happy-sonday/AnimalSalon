-- <테스트용> 회원테이블
CREATE TABLE MEMBER (
    ID VARCHAR2(50) PRIMARY KEY
    );

-- <테스트용> 매장정보테이블
CREATE TABLE SHOP_INFO (
    S_CODE VARCHAR2(30) PRIMARY KEY
    );

------ 예약내역테이블
CREATE TABLE BOOKING (
    B_CODE VARCHAR2(30),    -- 예약코드
    ID VARCHAR2(50),        -- 아이디
    O_CODE VARCHAR2(30),    -- 옵션코드
    M_CODE VARCHAR2(30),    -- 메뉴코드
    D_CODE VARCHAR2(30),    -- 디자이너코드
    S_CODE VARCHAR2(30),    -- 매장코드
    B_DATE DATE,            -- 예약일정(년월일,요일)
    B_TIME DATE,            -- 예약일정(시간)
    B_BEATY_TIME DATE,      -- 시술 소요시간
    B_PRICE NUMBER,         -- 예약 금액
    B_STATUS CHAR(1),       -- 예약 상태 / 0: 예약진행중, 1: 완료, 2: 취소(환불), 3: 삭제
    B_CANCEL_REASON VARCHAR2(20), -- 예약 취소 사유
    PRIMARY KEY (B_CODE),
    FOREIGN KEY (ID) REFERENCES MEMBER(ID)
    );
    
------ 메뉴테이블
CREATE TABLE SHOP_MENU (
    M_CODE VARCHAR2(30),    -- 메뉴코드
    S_CODE VARCHAR2(30),    -- 매장코드
    M_TYPE VARCHAR2(24),    -- 종분류 / 강아지 OR 고양이
    M_NAME VARCHAR2(50),    -- 메뉴명
    M_TIME DATE,            -- 소요시간
    M_PRICE NUMBER,         -- 금액
    M_INFO VARCHAR2(300),   -- 상품부가정보
    PRIMARY KEY (M_CODE),
    FOREIGN KEY (S_CODE) REFERENCES SHOP_INFO(S_CODE)
    );
    
------ 메뉴옵션테이블
CREATE TABLE SHOP_MENU_OPTION (
    O_CODE VARCHAR2(30), -- 옵션코드
    M_CODE VARCHAR2(30), -- 메뉴코드
    S_CODE VARCHAR2(30), -- 매장코드
    O_NAME VARCHAR2(30), -- 옵션명
    O_TIME DATE,         -- 추가 소요시간
    O_PRICE NUMBER,       -- 추가 금액
    PRIMARY KEY (O_CODE),
    FOREIGN KEY (M_CODE) REFERENCES SHOP_MENU(M_CODE),
    FOREIGN KEY (S_CODE) REFERENCES SHOP_INFO(S_CODE)
    );
    
------ 메뉴사진테이블
CREATE TABLE SHOP_MENU_PHOTO (
    M_CODE VARCHAR2(30),                -- 메뉴코드
    S_CODE VARCHAR2(30),                -- 매장코드
    M_P_PATH VARCHAR2(500) NOT NULL,    -- 사진파일경로
    M_P_SYSNAME VARCHAR2(100) NOT NULL, -- 사진파일시스템이름
    M_P_ORGNAME VARCHAR2(100) NOT NULL, -- 사진파일원본이름
    FOREIGN KEY (M_CODE) REFERENCES SHOP_MENU(M_CODE) ON DELETE CASCADE,
    FOREIGN KEY (S_CODE) REFERENCES SHOP_INFO(S_CODE) ON DELETE CASCADE
    );
