-- 매장 사진 정보의 대한 CREATE TABLE --

-- 4. 매장 사진 정보 TABLE

CREATE TABLE SHOP_PHOTO_TBL (
-- 매장 코드
S_CODE VARCHAR2(30) NOT NULL,
-- 매장 사진 파일 경로 
S_PHOTOPATH VARCHAR2(500) NOT NULL,
-- 매장 사진 파일 이름(파일이름 중복되지 않는 규칙 부여 필요)
S_PHOTONAME VARCHAR2(100) NOT NULL,
-- 매장 사진의 원래 파일명
S_PHOTONAME_ORIGIN VARCHAR2(100) NOT NULL); 

commit;

-- 4-1. 매장 디자이너의 매장 코드 외래키 생성

ALTER TABLE SHOP_PHOTO_TBL ADD CONSTRAINT
FK_SHOP_PHOTO_CODE FOREIGN KEY(S_CODE)REFERENCES SHOP_INFO_TBL(S_CODE);
commit;

