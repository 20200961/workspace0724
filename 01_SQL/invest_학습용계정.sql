DROP TABLE USERS;
DROP TABLE ASSET;
DROP TABLE TRADE_HISTORY;
DROP TABLE PORTFOLIO;
select * from USERS;
SELECT COUNT(*) FROM ASSET;

-- 사용자 계정, 잔액 관리, 로그인 인증
create table USERS(
    user_id VARCHAR2(20) PRIMARY KEY, -- 사용자 아이디
    password VARCHAR2(100) NOT NULL, -- 사용자 비밀번호
    name VARCHAR2(20) NOT NULL, -- 사용자 이름
    balance NUMBER(15,2) NOT NULL, -- 계좌 잔액
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP-- 등록일
);


-- 자산 정보 저장, 가격 변동 시 업데이트
create table ASSET(
    asset_id VARCHAR2(10) PRIMARY KEY, -- 코인 코드
    asset_name VARCHAR2(50) NOT NULL, -- 자산명
    current_price NUMBER(15,2) NOT NULL, -- 현재 가격
    volatility NUMBER(5,2) NOT NULL, -- 가격 변동 범위(%)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP-- 등록일
);

-- 거래 내역 기록, 포트폴리오 수익률 계산에 사용
create table TRADE_HISTORY(
    trade_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- 거래번호
    user_id VARCHAR2(20) NOT NULL, -- 거래한 사용자
    asset_id VARCHAR2(10) NOT NULL, -- 거래 자산
    trade_type VARCHAR2(4) NOT NULL, -- BUY/SELL
    price NUMBER(15,2) NOT NULL, -- 거래 가격
    all_price NUMBER(20,2) NOT NULL, -- 총 거래가격
    quantity NUMBER NOT NULL, -- 거래 수량
    trade_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 거래 시각
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES USERS(user_id),
    CONSTRAINT fk_asset FOREIGN KEY(asset_id) REFERENCES ASSET(asset_id)
);

-- 사용자별 자산 보유 현황 관리, 매수/매도 시 업데이트
create table PORTFOLIO(
    user_id VARCHAR2(20) NOT NULL, -- 사용자
    asset_id VARCHAR2(10) NOT NULL, -- 보유 자산
    quantity NUMBER NOT NULL, -- 보유 수량
    avg_price NUMBER(15,2) NOT NULL, -- 평균 매수 가격
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 최근 업데이트 시각
    CONSTRAINT pk_portfolio PRIMARY KEY(user_id, asset_id), -- 한 사용자가 같은 자산을 여러 줄로 가지는 것을 방지
    CONSTRAINT fk_port_user FOREIGN KEY (user_id) REFERENCES USERS(user_id),
    CONSTRAINT fk_port_asset FOREIGN KEY(asset_id) REFERENCES ASSET(asset_id)
);

SELECT * FROM ASSET;
SELECT * FROM TRADE_HISTORY;
SELECT * FROM USERS;

DESC ASSET;

DELETE FROM ASSET;
DELETE FROM PORTFOLIO;
DELETE FROM TRADE_HISTORY;

INSERT INTO ASSET (asset_id, asset_name, current_price, volatility)
VALUES ('BTC', 'Bitcoin', 150037481.4, 5.0);

INSERT INTO ASSET (asset_id, asset_name, current_price, volatility)
VALUES ('ETH', 'Ethereum', 6078342.2, 3.0);

INSERT INTO ASSET (asset_id, asset_name, current_price, volatility)
VALUES ('DOGE', 'Dogecoin', 294.7, 10.0);

INSERT INTO ASSET (asset_id, asset_name, current_price, volatility)
VALUES ('LTC', 'Litecoin', 149858.5, 6.0);

INSERT INTO ASSET (asset_id, asset_name, current_price, volatility)
VALUES ('XRP', 'Ripple', 3843.8, 7.0);

INSERT INTO ASSET (asset_id, asset_name, current_price, volatility)
VALUES ('ADA', 'Cardano', 1120.0, 4.0);

INSERT INTO ASSET (asset_id, asset_name, current_price, volatility)
VALUES ('DOT', 'Polkadot', 5124.2, 8.0);

INSERT INTO ASSET (asset_id, asset_name, current_price, volatility)
VALUES ('LINK', 'Chainlink', 32141.0, 5.5);

INSERT INTO ASSET (asset_id, asset_name, current_price, volatility)
VALUES ('XLM', 'Stellar', 347.2, 9.0);

INSERT INTO ASSET (asset_id, asset_name, current_price, volatility)
VALUES ('UNI', 'Uniswap', 9410.0, 6.5);


UPDATE ASSET
SET current_price = 150037481.4, volatility = 5.0
WHERE asset_id = 'BTC';

UPDATE ASSET
SET current_price = 6078342.2, volatility = 3.0
WHERE asset_id = 'ETH';

UPDATE ASSET
SET current_price = 294.7, volatility = 10.0
WHERE asset_id = 'DOGE';

UPDATE ASSET
SET current_price = 149858.5, volatility = 6.0
WHERE asset_id = 'LTC';

UPDATE ASSET
SET current_price = 3843.8, volatility = 7.0
WHERE asset_id = 'XRP';

UPDATE ASSET
SET current_price = 1120.0, volatility = 4.0
WHERE asset_id = 'ADA';

UPDATE ASSET
SET current_price = 5124.2, volatility = 8.0
WHERE asset_id = 'DOT';

UPDATE ASSET
SET current_price = 32141.0, volatility = 5.5
WHERE asset_id = 'LINK';

UPDATE ASSET
SET current_price = 347.2, volatility = 9.0
WHERE asset_id = 'XLM';

UPDATE ASSET
SET current_price = 9410.0, volatility = 6.5
WHERE asset_id = 'UNI';















