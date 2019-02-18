prompt Disabling triggers for ROLE...
alter table ROLE disable all triggers;
prompt Deleting ROLE...
delete from ROLE;
commit;
prompt Loading ROLE...
insert into ROLE (id, name, modules, createuser, createtime, updateuser, updatetime)
values (8, '第三方维修', '62-63-64-61', null, null, null, null);
insert into ROLE (id, name, modules, createuser, createtime, updateuser, updatetime)
values (1, '管理员', null, null, null, null, null);
commit;
prompt 2 records loaded
prompt Enabling triggers for ROLE...
alter table ROLE enable all triggers;
