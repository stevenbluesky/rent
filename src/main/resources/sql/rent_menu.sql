prompt Disabling triggers for RENT_MENU...
alter table RENT_MENU disable all triggers;
prompt Deleting RENT_MENU...
delete from RENT_MENU;
commit;
prompt Loading RENT_MENU...
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''16'', ''5'', ''0,5,'', ''单位档案'', 16, ''/../prUtilFile'', null, null, ''1'', null, ''1'', to_timestamp(''10-05-2017 09:49:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''10-05-2017 09:49:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''100'', ''64'', ''0,14,333,64,'', ''第三方维修'', 1, ''/sys/repaire/submit'', null, null, ''1'', null, ''1'', to_timestamp(''07-05-2017 16:22:02.383932'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''07-05-2017 16:22:02.383932'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''64'', ''333'', ''0,14'', ''第三方维修'', 1, ''/sys/repaire/submit'', null, null, ''1'', null, ''1'', to_timestamp(''07-05-2017 16:22:02.383932'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''07-05-2017 16:22:02.383932'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''101'', ''0'', ''0.'', ''报表统计'', 35, null, null, null, ''1'', null, ''1'', to_timestamp(''04-06-2017 08:43:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''04-06-2017 08:43:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''33'', ''6'', ''0.6.'', ''退租/验房'', 33, ''/../findMasterByCondition.do'', null, null, ''1'', null, ''1'', to_timestamp(''18-05-2017 13:49:40.000000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''18-05-2017 13:49:40.000000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''34'', ''0'', ''0,'', ''财务收费'', 34, null, null, null, ''1'', null, ''1'', to_timestamp(''19-05-2017 18:11:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''19-05-2017 18:11:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''17'', ''5'', ''0.5'', ''房源档案'', 17, ''/../prHouseFileByCondition'', null, null, ''1'', null, ''1'', to_timestamp(''11-05-2017 09:02:22.771000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''11-05-2017 09:03:22.771000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''102'', ''101'', ''0.101'', ''日报表'', 1, ''/../dayReport.do'', null, null, ''1'', null, ''1'', to_timestamp(''04-06-2017 08:43:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''04-06-2017 08:43:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''103'', ''101'', ''0.101'', ''月报表'', 2, ''/../monthReport.do'', null, null, ''1'', null, ''1'', to_timestamp(''04-06-2017 08:43:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''04-06-2017 08:43:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''104'', ''101'', ''0.101'', ''租户统计'', 3, ''/../renterReport.do'', null, null, ''1'', null, ''1'', to_timestamp(''04-06-2017 08:43:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''04-06-2017 08:43:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''18'', ''5'', ''0,5'', ''租户档案'', 18, ''/../prMasterFile'', null, null, ''1'', null, ''1'', to_timestamp(''07-05-2017 06:45:22.754000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''07-05-2017 06:45:22.754000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''32'', ''2'', ''0.2.'', ''房源信息'', 32, ''/../findPrHouseByEstatePaged.do'', null, null, ''1'', null, ''1'', to_timestamp(''17-05-2017 14:13:11.000000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''17-05-2017 14:13:11.000000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''35'', ''34'', ''0,34,'', ''日常合同扣租'', 35, ''/../dailyContractRental'', null, null, ''1'', null, ''1'', to_timestamp(''19-05-2017 18:11:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''19-05-2017 18:11:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''36'', ''34'', ''0,34,'', ''租金核算'', 36, ''/../rentAccounting'', null, null, ''1'', null, ''1'', to_timestamp(''19-05-2017 18:11:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''19-05-2017 18:11:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''37'', ''34'', ''0,34,'', ''退租结算清帐'', 37, ''/../leaseSettlementClearing'', null, null, ''1'', null, ''1'', to_timestamp(''19-05-2017 18:11:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''19-05-2017 18:11:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''38'', ''34'', ''0,34,'', ''租金调整审核'', 38, ''/../rentAdjustmentAudit'', null, null, ''1'', null, ''1'', to_timestamp(''19-05-2017 18:11:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''19-05-2017 18:11:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''81'', ''3'', ''0.3.'', ''门锁对码管理'', 81, ''/../findCode.do'', null, null, ''1'', null, ''1'', to_timestamp(''01-06-2017 15:52:12.000000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''01-06-2017 15:52:12.000000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''2'', ''0'', ''0,'', ''房源管理'', 2, null, null, null, ''1'', null, ''1'', to_timestamp(''07-05-2017 06:45:22.511000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''07-05-2017 06:45:22.511000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''3'', ''0'', ''0,'', ''租户管理'', 3, null, null, null, ''1'', null, ''1'', to_timestamp(''07-05-2017 06:45:22.531000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''07-05-2017 06:45:22.531000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''62'', ''3'', ''0.3.'', ''业务记录管理'', 62, ''/../findCheckInRecord.do'', null, null, ''1'', null, ''1'', to_timestamp(''21-05-2017 11:43:37.000000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''21-05-2017 11:43:37.000000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''5'', ''0'', ''0,'', ''档案管理'', 5, null, null, null, ''1'', null, ''1'', to_timestamp(''07-05-2017 06:45:22.598000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''07-05-2017 06:45:22.598000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''6'', ''0'', ''0,'', ''解约退租管理'', 6, null, null, null, ''1'', null, ''1'', to_timestamp(''07-05-2017 06:45:22.626000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''07-05-2017 06:45:22.626000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''63'', ''3'', ''0.3.'', ''续租管理'', 63, ''/../findReletPaged.do'', null, null, ''1'', null, ''1'', to_timestamp(''21-05-2017 11:43:43.000000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''21-05-2017 11:43:43.000000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''1'', ''0'', ''0,1,'', ''台账'', -1, null, null, null, ''1'', null, ''1'', to_timestamp(''04-06-2017 08:43:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''04-06-2017 08:43:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''66'', ''1'', ''0,65,'', ''台账'', 66, ''/../ledger'', null, null, ''1'', null, ''1'', to_timestamp(''04-06-2017 08:43:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''04-06-2017 08:43:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''8'', ''0'', ''0,'', ''报修管理'', 8, null, null, null, ''1'', null, ''1'', to_timestamp(''07-05-2017 06:45:22.737000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''07-05-2017 06:45:22.737000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''11'', ''8'', ''0,8'', ''报修登记'', 11, ''/sys/rent/list/'', null, null, ''1'', null, ''1'', to_timestamp(''07-05-2017 06:45:22.754000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''07-05-2017 06:45:22.754000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''12'', ''8'', ''0,8,'', ''现场查验'', 12, ''/sys/repaire/check'', null, null, ''1'', null, ''1'', to_timestamp(''07-05-2017 06:45:22.771000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''07-05-2017 06:45:22.771000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''13'', ''8'', ''0,8,'', ''报修管理'', 13, ''/sys/repaire/manage'', null, null, ''1'', null, ''1'', to_timestamp(''07-05-2017 06:45:22.783000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''07-05-2017 06:45:22.783000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values (''15'', ''3'', ''0,1,'', ''入住受理'', 15, ''/../prHouseDrawing'', null, null, ''1'', null, ''1'', to_timestamp(''09-05-2017 08:43:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), ''1'', to_timestamp(''09-05-2017 08:43:54.524000'', ''dd-mm-yyyy hh24:mi:ss.ff''), null, ''0'');
commit;
prompt 30 records loaded
prompt Enabling triggers for RENT_MENU...
alter table RENT_MENU enable all triggers;
