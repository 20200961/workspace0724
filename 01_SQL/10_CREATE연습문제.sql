CREATE USER C##KH IDENTIFIED BY KH;

GRANT CONNECT, RESOURCE TO C##KH;

ALTER USER C##KH DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS;
-- KH계정으로 전환
--------------------------------------------------------------------------------
-- 테이블을 복제할 수 있다. 여기서부터 KH계정으로 진행함
CREATE TABLE EMPLOYEE_COPY
AS (SELECT * FROM EMPLOYEE);

-- CREATE TABLE 테이블명 AS SELECT문
-- AS 뒤에오는 SELECT문의 결과를 기반으로 테이블을 생성하겠다.

DROP TABLE EMPLOYEE_COPY;

CREATE TABLE EMPLOYEE_COPY
AS (SELECT * FROM EMPLOYEE WHERE 1=0);


SELECT * FROM EMPLOYEE_COPY;

--------------------------------------------------------------------------------

/*
    테이블이 생성된 후에 제약조건을 수정하는 방법
    ALTER TABLE 테이블명 변경할 내용
    
    제약조건 추가방법
    - PRIMARY KEY : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] PRIMARY KEY(컬럼명);
    - FOREIGN KEY : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] FOREIGN KEY(컬럼명) REFERENCES 참조할 테이블 명(컬럼명);
    - UNIQUE : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] UNIQUE(컬럼명);
    - CHECK : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] CHECK(컬럼에 대한 조건식);
    
    위의 제약조건을 제거하려면
    - ALTER TABLE 테이블명 DROP CONSTRAINT 제약조건 이름;
    - NOT NULL : ALTER TABLE 테이블명 MODIFY 컬럼명 NOT NULL;
                 ALTER TABLE 테이블명 MODIFY 컬럼명 NULL;
*/

-- EMPLOYEE_COPY 테이블에 PRIMARY KEY 제약조건 추가(EMP_ID)
ALTER TABLE EMPLOYEE_COPY ADD PRIMARY KEY(EMP_ID);

-- EMPLOYEE 테이블에 DEPT_CODE에 외래키제약조건을 추가(DEPT_ID를 참조)
ALTER TABLE EMPLOYEE ADD CONSTRAINT DEPT_FK FOREIGN KEY(DEPT_CODE) REFERENCES DEPARTMENT(DEPT_ID);


-- 실습문제 --
-- 도서관리 프로그램을 만들기 위한 테이블들 만들기 --
-- 이때, 제약조건에 이름을 부여할 것
-- 각 컬럼에 주석 달기

-- 1. 출판사들에 대한 데이터를 담기 위한 출판사 테이블(TB_PUBLISHER)
-- 컬럼 : PUB_NO (출판사 번호) - 기본키 (PUBLISHER_PK)
--        PUB_NAME (출판사명) - NOT NULL (PUBLISHER_NN)
--        PHONE (출판사 전화번호) - 제약조건 없음

-- 샘플 3개 정도 생성
DROP TABLE TB_PUBLISHER;

CREATE TABLE TB_PUBLISHER(
    PUB_NO INT NOT NULL PRIMARY KEY,
    PUB_NAME VARCHAR(30) CONSTRAINT PUBLISHER_NN NOT NULL,
    PHONE VARCHAR2(13)
);
    
INSERT INTO TB_PUBLISHER VALUES (1, '출판사A', '010-1111-1111');
INSERT INTO TB_PUBLISHER VALUES (2, '출판사B', '010-2222-2222');
INSERT INTO TB_PUBLISHER VALUES (3, '출판사C', '010-3333-3333');

SELECT * FROM TB_PUBLISHER;


-- 2. 도서들에 대한 데이터를 담기 위한 도서 테이블(TB_BOOK)
-- 컬럼 : BK_NO (도서번호) - 기본키 (BOOK_PK)
--        BK_TITLE (도서명) - NOT NULL (BOOK__NN_TITLE)
--        BK_AUTHOR (저자명) - NOT NULL (BOOK__NN_AUTHOR)
--        BK_PRICE (가격) - 제약조건 없음
--        BK_PUB_NO (출판사 번호) - 외래키 (BOOK_FK), TB_PUBLISHER 테이블 참조
--                                 이때 참조하고 있는 부모데이터 삭제 시 자식데이터도 삭제되도록 옵션 지정

-- 샘플 5개 정도 생성
DROP TABLE TB_BOOK;

CREATE TABLE TB_BOOK(
    BK_NO NUMBER PRIMARY KEY,
    BK_TITLE VARCHAR(30) CONSTRAINT BOOK__NN_TITLE NOT NULL,
    BK_AUTHOR VARCHAR(20) CONSTRAINT BOOK__NN_AUTHOR NOT NULL,
    BK_PRICE NUMBER,
    BK_PUB_NO NUMBER,
    CONSTRAINT BOOK_FK FOREIGN KEY (BK_PUB_NO) REFERENCES TB_PUBLISHER(PUB_NO)
    ON DELETE CASCADE
)

INSERT INTO TB_BOOK VALUES (1, '도서A', '저자1', 15000, 1);
INSERT INTO TB_BOOK VALUES (2, '도서B', '저자2', 18000, 2);
INSERT INTO TB_BOOK VALUES (3, '도서C', '저자3', 20000, 3);
INSERT INTO TB_BOOK VALUES (4, '도서D', '저자4', 22000, NULL);
INSERT INTO TB_BOOK VALUES (5, '도서E', '저자5', 25000, NULL);

SELECT * FROM TB_BOOK;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
-- 3. 회원에 대한 데이터를 담기 위한 회원 테이블(TB_MEMBER)
-- 컬럼 : MEMBER_NO (회원번호) - 기본키 (MEMBER_PK)
--        MEMBER_ID (아이디) - 중복금지 (MEMBER_UQ_ID)
--        MEMBER_PWD (비밀번호) - NOT NULL (MEMBER_NN_PWD)
--        MEMBER_NAME (회원명) - NOT NULL (MEMBER_NN_NAME)
--        GENDER (성별) - M 또는 F로 제한 (MEMBER_CK_GEN)
--        ADDRESS (주소) - 제약조건 없음
--        PHONE (연락처) - 제약조건 없음
--        STATUS (탈퇴여부) - 기본값 N, N 또는 Y만 허용 (MEMBER_CK_STA)
--        ENROLL_DATE (가입일) - 기본값 SYSDATE, NOT NULL (MEMBER_NN_EN)

-- 샘플 5개 정도 생성
DROP TABLE TB_MEMBER;

CREATE TABLE TB_MEMBER(
    MEMBER_NO NUMBER PRIMARY KEY,
    MEMBER_ID VARCHAR(20) CONSTRAINT MEMBER_UQ_ID UNIQUE,
    MEMBER_PWD VARCHAR(20) CONSTRAINT MEMBER_NN_PWD NOT NULL,
    MEMBER_NAME VARCHAR(30) CONSTRAINT MEMBER_NN_NAME NOT NULL,
    GENDER CHAR(3) CONSTRAINT MEMBER_CK_GEN CHECK(GENDER IN ('M','F')),
    ADDRESS VARCHAR(50),
    PHONE VARCHAR2(13),
    STATUS CHAR(1) DEFAULT 'N' CONSTRAINT MEMBER_CK_STA CHECK(STATUS IN ('N','Y')),
    ENROLL_DATE DATE DEFAULT SYSDATE CONSTRAINT MEMBER_NN_EN NOT NULL
)

INSERT INTO TB_MEMBER VALUES (1, 'user01', 'pwd01', '홍길동', 'M', '서울', '010-1234-1234', DEFAULT, DEFAULT);
INSERT INTO TB_MEMBER VALUES (2, 'user02', 'pwd02', '김철수', 'M', '부산', '010-5678-5678', 'N', DEFAULT);
INSERT INTO TB_MEMBER VALUES (3, 'user03', 'pwd03', '이영희', 'F', '인천', '010-1111-2222', 'Y', DEFAULT);
INSERT INTO TB_MEMBER VALUES (4, 'user04', 'pwd04', '박민수', 'M', '대구', '010-3333-4444', 'Y', DEFAULT);
INSERT INTO TB_MEMBER VALUES (5, 'user05', 'pwd05', '최지우', 'F', '광주', '010-5555-6666', DEFAULT, DEFAULT);

SELECT * FROM TB_MEMBER;

-- 4. 어떤 회원이 어떤 도서를 대여했는지에 대한 대여목록 테이블(TB_RENT)
-- 컬럼 : RENT_NO (대여번호) - 기본키 (RENT_PK)
--        RENT_MEM_NO (대여회원번호) - 외래키 (RENT_FK_MEM), TB_MEMBER 참조
--                                     부모 데이터 삭제 시 자식데이터 값이 NULL이 되도록 지정
--        RENT_BOOK_NO (대여도서번호) - 외래키 (RENT_FK_BOOK), TB_BOOK 참조
--                                      부모 데이터 삭제 시 자식데이터 값이 NULL이 되도록 지정
--        RENT_DATE (대여일) - 기본값 SYSDATE

-- 샘플 3개 정도 생성
DROP TABLE TB_RENT;

CREATE TABLE TB_RENT(
    RENT_NO NUMBER PRIMARY KEY,
    RENT_MEM_NO NUMBER,
    CONSTRAINT RENT_FK_MEM FOREIGN KEY (RENT_MEM_NO) REFERENCES TB_MEMBER(MEMBER_NO) ON DELETE SET NULL,
    RENT_BOOK_NO NUMBER,
    CONSTRAINT RENT_FK_BOOK FOREIGN KEY (RENT_BOOK_NO) REFERENCES TB_BOOK(BK_NO) ON DELETE SET NULL,
    RENT_DATE DATE DEFAULT SYSDATE
)

INSERT INTO TB_RENT VALUES (1, 1, 3, DEFAULT);
INSERT INTO TB_RENT VALUES (2, 2, 1, DEFAULT);
INSERT INTO TB_RENT VALUES (3, 3, NULL, DEFAULT);

SELECT * FROM TB_RENT;

-- 대여목록 조회
-- TB_RENT, TB_MEMBER, TB_BOOK 테이블을 JOIN하여 대여 정보를 조회

SELECT RENT_NO, RENT_MEM_NO, MEMBER_NAME, BK_TITLE FROM TB_RENT
JOIN TB_MEMBER ON RENT_MEM_NO = MEMBER_NO
JOIN TB_BOOK ON RENT_BOOK_NO = BK_NO;






 





















