prompt Disabling triggers for PRH_PAYCODE...
alter table PRH_PAYCODE disable all triggers;
prompt Deleting PRH_PAYCODE...
delete from PRH_PAYCODE;
commit;
prompt Loading PRH_PAYCODE...
insert into PRH_PAYCODE (id, name)
values (1, '现金');
insert into PRH_PAYCODE (id, name)
values (2, '刷卡');
insert into PRH_PAYCODE (id, name)
values (3, '转账');
commit;
prompt 3 records loaded
prompt Enabling triggers for PRH_PAYCODE...
alter table PRH_PAYCODE enable all triggers;
