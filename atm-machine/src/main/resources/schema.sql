DROP TABLE IF EXISTS ACCOUNTS;  
CREATE TABLE ACCOUNTS (  
ACCOUNT_NO VARCHAR(100) PRIMARY KEY,  
PIN INT NOT NULL,  
OPENING_BALANCE INT NOT NULL,
OVER_DRAFT INT NOT NULL
); 

DROP TABLE IF EXISTS ATM_NOTES;  
CREATE TABLE ATM_NOTES (  
NOTE_TYPE INT AUTO_INCREMENT  PRIMARY KEY,  
NOTE_COUNT INT NOT NULL
); 

