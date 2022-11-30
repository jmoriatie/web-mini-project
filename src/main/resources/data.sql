insert into authority_tb (authority_name) values ('ROLE_USER');
insert into authority_tb (authority_name) values ('ROLE_ADMIN');

insert into account_tb (account_id, account_pw, account_name) values("test", "$2a$10$2jW77z3DUzCkhQa6vZ/OMeHulTMPUeq23OEXThv5Rd/GW49iYCexS", "test_name");
insert into account_tb (account_id, account_pw, account_name) values("a", "$2a$10$WDot1/ABhfX9xaO/uDvq5.OcZt110Si5BXzonHAvv8L6XtXuZQzMC", "GAZZA_NAME");


insert into item_tb (item_name, price, quantity, account_id) values('test1', 1000, 10, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test2', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test3', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test4', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test5', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test6', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test7', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test8', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test9', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test10', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test11', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test12', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test13', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test14', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test15', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test16', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test17', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test18', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test19', 9999, 20, 'test' );
insert into item_tb (item_name, price, quantity, account_id ) values('test20', 9999, 20, 'test' );

insert into item_tb (item_name, price, quantity, account_id ) values('testGAZZA1', 9999, 20, 'a' );
insert into item_tb (item_name, price, quantity, account_id ) values('testGAZZA2', 1111, 20, 'a' );
insert into item_tb (item_name, price, quantity, account_id ) values('testGAZZA3', 2222, 20, 'a' );


insert into account_authority_tb(id, authority_name) values(1,'ROLE_ADMIN');
insert into account_authority_tb(id, authority_name) values(1,'ROLE_USER');

