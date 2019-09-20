insert into SYS_USER (user_id,username, password) values (1,'admin', 'admin');
insert into SYS_USER (user_id,username, password) values (2,'abel', 'abel');

insert into SYS_ROLE(role_id,role_name) values(1,'ROLE_ADMIN');
insert into SYS_ROLE(role_id,role_name) values(2,'ROLE_USER');

insert into SYS_user_role(user_id,ROLE_ID) values(1,1);
insert into SYS_user_role(USER_ID,ROLE_ID) values(2,2);

BEGIN;
INSERT INTO `Sys_permission` VALUES ('1', 'ROLE_HOME', 'home', '/', null), ('2', 'ROLE_ADMIN', 'ABel', '/admin', null);
COMMIT;

BEGIN;
INSERT INTO `Sys_permission_role` VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '2', '1');
COMMIT;

delete from sys_permission;
delete from sys_role;
delete from sys_permission_role;
delete from sys_user;
delete from sys_user_role;