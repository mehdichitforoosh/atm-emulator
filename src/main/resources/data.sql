-- user sample records
INSERT INTO users (id,first_name,last_name,description) VALUES (1,'Mehdi','Chitforoosh','Senior Java Developer');
INSERT INTO users (id,first_name,last_name,description) VALUES (2,'ALi','Rezaei','');
INSERT INTO users (id,first_name,last_name,description) VALUES (3,'Morteza','Kalantar','Senior Frontend Developer');
INSERT INTO users (id,first_name,last_name,description) VALUES (4,'Ali','Chitforoosh','');
INSERT INTO users (id,first_name,last_name,description) VALUES (5,'Mohammad','Javadi','');
-- bank sample records
INSERT INTO banks (id,name,branch_number,address) VALUES (1,'Melli','110','Iran, Yazd');
INSERT INTO banks (id,name,branch_number,address) VALUES (2,'Melli','120','Iran, Yazd');
INSERT INTO banks (id,name,branch_number,address) VALUES (3,'Mellat','1112','Iran, Yazd');
INSERT INTO banks (id,name,branch_number,address) VALUES (4,'Mellat','1122','Iran, Yazd');
INSERT INTO banks (id,name,branch_number,address) VALUES (5,'Sepah','10','Iran, Yazd');
INSERT INTO banks (id,name,branch_number,address) VALUES (6,'Pasargad','3456','Iran, Yazd');
-- account sample records
INSERT INTO accounts (id,account_number,balance,bank_id,user_id) VALUES (1,'43897345',102000,1,1);
INSERT INTO accounts (id,account_number,balance,bank_id,user_id) VALUES (2,'43897346',12000,1,2);
INSERT INTO accounts (id,account_number,balance,bank_id,user_id) VALUES (3,'1000534',8000,3,1);
INSERT INTO accounts (id,account_number,balance,bank_id,user_id) VALUES (4,'1000876',92600,3,4);
INSERT INTO accounts (id,account_number,balance,bank_id,user_id) VALUES (5,'28978887',132000,6,5);
INSERT INTO accounts (id,account_number,balance,bank_id,user_id) VALUES (6,'28978957',1000,6,3);
INSERT INTO accounts (id,account_number,balance,bank_id,user_id) VALUES (7,'2000967',38478,5,3);
INSERT INTO accounts (id,account_number,balance,bank_id,user_id) VALUES (8,'1000345',102000,4,1);
-- card sample records
INSERT INTO cards (id,card_number,pin,expiration_date,active,account_id) VALUES (1,'9856342578786543','4567','2022-06-22 19:10:25-07',true,1);
INSERT INTO cards (id,card_number,pin,expiration_date,active,account_id) VALUES (2,'9856342578325498','9345','2021-12-22 20:10:25-07',true,2);
INSERT INTO cards (id,card_number,pin,expiration_date,active,account_id) VALUES (3,'8765342578323421','2364','2023-06-22 12:10:25-07',true,5);
INSERT INTO cards (id,card_number,pin,expiration_date,active,account_id) VALUES (4,'2345879078321112','1178','2021-06-22 19:10:25-07',false,7);
-- atm sample records
INSERT INTO atms (id,atm_number,available_cash) VALUES (1,'100',10000000);
INSERT INTO atms (id,atm_number,available_cash) VALUES (2,'101',50000000);
INSERT INTO atms (id,atm_number,available_cash) VALUES (3,'101',10000);



