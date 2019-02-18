prompt Disabling triggers for ESTATE_TYPE...
alter table ESTATE_TYPE disable all triggers;
prompt Deleting ESTATE_TYPE...
delete from ESTATE_TYPE;
commit;
prompt Loading ESTATE_TYPE...
insert into ESTATE_TYPE (id, name)
values (1, '公租');
insert into ESTATE_TYPE (id, name)
values (2, '廉租房');
insert into ESTATE_TYPE (id, name)
values (3, '限价房');
commit;
prompt 3 records loaded
prompt Enabling triggers for ESTATE_TYPE...
alter table ESTATE_TYPE enable all triggers;

