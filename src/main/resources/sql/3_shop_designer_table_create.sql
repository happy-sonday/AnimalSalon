-- 매장 정보의 대한 CREATE TABLE --

-- 3. 매장의 등록된 디자이너 정보 TABLE
-- 3-1. D_DAY_OFF (1202에 추가)

CREATE TABLE SHOP_DESIGNER_TBL (
-- 매장 코드
S_CODE VARCHAR2(30),
-- 디자이너 코드 
D_CODE VARCHAR2(30) NOT NULL PRIMARY KEY,
-- 디자이너 이름
D_NAME VARCHAR2(30) NOT NULL,
-- 디자이너 정보(소개글)
D_INFO VARCHAR2(2000),
-- 디자이너 리뷰 카운트(리뷰글의 해당 디자이너 Total)
D_REVIEW_COUNT NUMBER(30) DEFAULT 0,
-- 리뷰글의 별점의 평균
D_AVG_SCORE NUMBER(30)DEFAULT 0, 
-- 디자이너 휴무일
D_DAY_OFF CHAR(10) NOT NULL);

commit;

-- 3-1. 매장 디자이너의 매장 코드 외래키 생성

ALTER TABLE SHOP_DESIGNER_TBL ADD CONSTRAINT
FK_DESIGER_S_CODE FOREIGN KEY(S_CODE)REFERENCES SHOP_INFO_TBL(S_CODE);
commit;

-- 3-2.매장 디자이너 SEQUENCE 생성

CREATE SEQUENCE SHOP_DESIGNER_TBL_SEQ
	START WITH 1
    INCREMENT BY 1
    MAXVALUE 99999
    NOCYCLE; 
    commit;
    

SELECT * FROM SHOP_DESIGNER_TBL;