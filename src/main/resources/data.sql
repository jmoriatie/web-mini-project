insert into authority_tb (authority_name) values ('ROLE_USER');
insert into authority_tb (authority_name) values ('ROLE_ADMIN');

insert into account_tb (account_id, account_pw, account_name) values("test", "$2a$10$2jW77z3DUzCkhQa6vZ/OMeHulTMPUeq23OEXThv5Rd/GW49iYCexS", "test_name");

insert into item_tb (item_name, price, quantity, account_id) values('test1', 1000, 10, 'test');
insert into item_tb (item_name, price, quantity, account_id) values('test2', 9999, 20, 'test');

insert into account_authority_tb(id, authority_name) values(1,'ROLE_ADMIN');
insert into account_authority_tb(id, authority_name) values(1,'ROLE_USER');
