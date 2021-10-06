DROP DATABASE IF EXISTS BankingApp;
CREATE DATABASE BankingApp;

USE BankingApp;


DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS customer_accounts;


--   DROP TABLE  users;
--   DROP TABLE  accounts;
--   DROP TABLE customer_accounts;

-- TRUNCATE TABLE customer_accounts; 
-- TRUNCATE TABLE users;
-- TRUNCATE TABLE accounts;

CREATE TABLE customer_accounts (
	junction_id INT AUTO_INCREMENT,
	user_id 	INT NOT NULL,
	account_id  INT NOT NULL,
	INDEX (user_id),
	INDEX (account_id),
	CONSTRAINT customer_accounts_fk PRIMARY KEY (junction_id)
);


CREATE TABLE users (
	user_id 	INT NOT NULL, 
	username	VARCHAR(40),
	email	 	VARCHAR(40),
	password    VARCHAR(40),
	CONSTRAINT users_pk PRIMARY KEY (user_id),
	CONSTRAINT users_customer_accounts_fk FOREIGN KEY (user_id) REFERENCES customer_accounts (user_id)
);

CREATE TABLE accounts (
	account_id 	INT NOT NULL,
	account_type VARCHAR(40),
	balance 	DECIMAL(10,2),
	CONSTRAINT accounts_pk PRIMARY KEY (account_id),
	CONSTRAINT accounts_customer_accounts_fk FOREIGN KEY (account_id) REFERENCES customer_accounts (account_id)
);

ALTER TABLE users ADD CONSTRAINT U_users UNIQUE (username);

INSERT INTO customer_accounts (user_id, account_id) VALUES (1, 500001);
INSERT INTO customer_accounts (user_id, account_id) VALUES (2, 500002);
INSERT INTO customer_accounts (user_id, account_id) VALUES (3, 500003);
INSERT INTO customer_accounts (user_id, account_id) VALUES (4, 500004);
INSERT INTO customer_accounts (user_id, account_id) VALUES (5, 500005);

INSERT INTO users (user_id, username, email, password) VALUES (1, "Joy45", "Adams234@email.com", "P4ssW0rd1");
INSERT INTO users (user_id, username, email, password) VALUES (2, "Davey9", "DmanAdams992@email.com", "Ruthless123");
INSERT INTO users (user_id, username, email, password) VALUES (3, "Love12", "Love778@email.com", "CocaCola34");
INSERT INTO users (user_id, username, email, password) VALUES (4, "Olive12", "West226@email.com", "ThunderSt0rm1");
INSERT INTO users (user_id, username, email, password) VALUES (5, "Precious", "prep123@email.com", "Lightyears1");

INSERT INTO accounts (account_id, account_type, balance) VALUES (500001, "checking", 150.00);
INSERT INTO accounts (account_id, account_type, balance) VALUES (500002, "checking", 2500.00);
INSERT INTO accounts (account_id, account_type, balance) VALUES (500003, "savings", 20000.00);
INSERT INTO accounts (account_id, account_type, balance) VALUES (500004, "checking", 20.00);
INSERT INTO accounts (account_id, account_type, balance) VALUES (500005, "savings", 500.00);



-- SELECT * FROM users u 
-- JOIN customer_accounts ca ON u.user_id = ca.user_id 
-- JOIN accounts a ON ca.account_id = a.account_id 
-- ORDER BY a.balance DESC;
-- 
-- 
-- SELECT u.username, a.account_id, a.balance
-- FROM users u 
-- JOIN customer_accounts ca ON u.user_id = ca.user_id 
-- JOIN accounts a ON ca.account_id = a.account_id
-- ORDER BY a.balance DESC;
-- 
-- select * from accounts a 
-- join customer_accounts ca ON a.account_id = ca.account_id 

 
