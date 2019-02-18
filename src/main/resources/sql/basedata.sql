prompt Disabling triggers for ESTATE_TYPE...
alter table ESTATE_TYPE disable all triggers;
prompt Disabling triggers for MODULE...
alter table MODULE disable all triggers;
prompt Disabling triggers for PRH_PAYCODE...
alter table PRH_PAYCODE disable all triggers;
prompt Disabling triggers for RENT_MENU...
alter table RENT_MENU disable all triggers;
prompt Disabling triggers for ROLE...
alter table ROLE disable all triggers;
prompt Disabling triggers for USERS...
alter table USERS disable all triggers;
prompt Disabling foreign key constraints for USERS...
alter table USERS disable constraint SYS_C005239;
prompt Deleting USERS...
delete from USERS;
commit;
prompt Deleting ROLE...
delete from ROLE;
commit;
prompt Deleting RENT_MENU...
delete from RENT_MENU;
commit;
prompt Deleting PRH_PAYCODE...
delete from PRH_PAYCODE;
commit;
prompt Deleting MODULE...
delete from MODULE;
commit;
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
prompt Loading MODULE...
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (11, '业务记录管理', 62, 3, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (12, '续租管理', 63, 3, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (13, '门锁对码管理', 81, 3, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (14, '打印合同', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (16, '修改', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (17, '删除', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (67, '电子附件', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (68, '下载', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (69, '删除附件', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (70, '上传', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (76, '打印合同', null, 12, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (81, '查看租户信息', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (2, '房源管理', 2, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (3, '租户管理', 3, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (4, '解约退租管理', 6, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (5, '档案管理', 5, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (6, '财务收费', 34, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (7, '台账', 1, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (8, '报修管理', 8, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (9, '房源信息', 32, 2, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (100, '新增', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (18, '续租申请', null, 12, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (20, '修改', null, 12, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (21, '删除', null, 12, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (22, '门卡管理', null, 13, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (23, '开卡', null, 13, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (24, '生成身份证对码', null, 13, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (25, '挂失卡', null, 13, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (41, '单位租户明细', null, 26, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (42, '查看房间历史租户', null, 27, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (43, '查看配套设备', null, 27, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (75, '历史身份证对码', null, 13, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (45, '批量添加设备(模板)', null, 27, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (46, '结算', null, 29, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (47, '核算', null, 30, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (48, '退租结算', null, 31, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (49, '复选调价', null, 32, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (50, '批量调价', null, 32, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (51, '报修登记', 11, 8, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (52, '现场查验', 12, 8, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (53, '报修管理', 13, 8, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (54, '报修申请', null, 51, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (55, '现场查验', null, 52, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (56, '打印单据', null, 52, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (57, '提交维修方', null, 53, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (58, '打印交办单', null, 53, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (59, '验收', null, 53, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (60, '打印单据', null, 53, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (61, '第三方维修', null, -1, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (62, '第三方维修', null, 61, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (63, '维修中', null, 62, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (64, '维修完成', null, 62, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (74, '身份证挂失确认', null, 13, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (78, '日报表', 102, 77, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (79, '月报表', 103, 77, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (80, '租户统计', 104, 77, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (77, '报表统计', 101, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (1, '全部', null, null, 0, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (10, '入住受理', 15, 3, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (34, '退租/验房', 33, 4, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (35, '申请退租', null, 34, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (36, '确认退租', null, 34, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (37, '打印单据', null, 34, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (38, '报修申请', null, 34, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (39, '修改', null, 34, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (40, '删除', null, 34, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (71, '写设房号卡', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (72, '写锁号设置卡', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (26, '单位档案', 16, 5, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (27, '房源档案', 17, 5, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (28, '租户档案', 18, 5, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (29, '日常合同扣租', 35, 6, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (30, '租金核算', 36, 6, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (31, '退租结算清账', 37, 6, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (32, '租金调整审核', 38, 6, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (33, '台账', 66, 7, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (101, '修改', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (102, '删除', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (103, '批量导入', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (104, '锁定', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (105, '解锁', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (73, '白卡查询', null, 13, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (65, '入住登记', null, 10, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (66, '租户信息查看', null, 10, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (107, '作废', null, 29, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (108, '打印对码', null, 13, 3, null, null, null, null);
commit;
prompt 86 records loaded
prompt Loading PRH_PAYCODE...
insert into PRH_PAYCODE (id, name)
values (1, '现金');
insert into PRH_PAYCODE (id, name)
values (2, '刷卡');
insert into PRH_PAYCODE (id, name)
values (3, '转账');
commit;
prompt 3 records loaded
prompt Loading RENT_MENU...
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('16', '5', '0,5,', '单位档案', 16, '/../prUtilFile', null, null, '1', null, '1', to_timestamp('10-05-2017 09:49:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('10-05-2017 09:49:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('100', '64', '0,14,333,64,', '第三方维修', 1, '/sys/repaire/submit', null, null, '1', null, '1', to_timestamp('07-05-2017 16:22:02.383932', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('07-05-2017 16:22:02.383932', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('64', '333', '0,14', '第三方维修', 1, '/sys/repaire/submit', null, null, '1', null, '1', to_timestamp('07-05-2017 16:22:02.383932', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('07-05-2017 16:22:02.383932', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('101', '0', '0.', '报表统计', 35, null, null, null, '1', null, '1', to_timestamp('04-06-2017 08:43:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('04-06-2017 08:43:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('33', '6', '0.6.', '退租/验房', 33, '/../findMasterByCondition.do', null, null, '1', null, '1', to_timestamp('18-05-2017 13:49:40.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('18-05-2017 13:49:40.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('34', '0', '0,', '财务收费', 34, null, null, null, '1', null, '1', to_timestamp('19-05-2017 18:11:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('19-05-2017 18:11:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('17', '5', '0.5', '房源档案', 17, '/../prHouseFileByCondition', null, null, '1', null, '1', to_timestamp('11-05-2017 09:02:22.771000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('11-05-2017 09:03:22.771000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('102', '101', '0.101', '日报表', 1, '/../dayReport.do', null, null, '1', null, '1', to_timestamp('04-06-2017 08:43:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('04-06-2017 08:43:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('103', '101', '0.101', '月报表', 2, '/../monthReport.do', null, null, '1', null, '1', to_timestamp('04-06-2017 08:43:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('04-06-2017 08:43:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('104', '101', '0.101', '租户统计', 3, '/../renterReport.do', null, null, '1', null, '1', to_timestamp('04-06-2017 08:43:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('04-06-2017 08:43:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('18', '5', '0,5', '租户档案', 18, '/../prMasterFile', null, null, '1', null, '1', to_timestamp('07-05-2017 06:45:22.754000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('07-05-2017 06:45:22.754000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('32', '2', '0.2.', '房源信息', 32, '/../findPrHouseByEstatePaged.do', null, null, '1', null, '1', to_timestamp('17-05-2017 14:13:11.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('17-05-2017 14:13:11.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('35', '34', '0,34,', '日常合同扣租', 35, '/../dailyContractRental', null, null, '1', null, '1', to_timestamp('19-05-2017 18:11:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('19-05-2017 18:11:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('36', '34', '0,34,', '租金核算', 36, '/../rentAccounting', null, null, '1', null, '1', to_timestamp('19-05-2017 18:11:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('19-05-2017 18:11:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('37', '34', '0,34,', '退租结算清帐', 37, '/../leaseSettlementClearing', null, null, '1', null, '1', to_timestamp('19-05-2017 18:11:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('19-05-2017 18:11:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('38', '34', '0,34,', '租金调整审核', 38, '/../rentAdjustmentAudit', null, null, '1', null, '1', to_timestamp('19-05-2017 18:11:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('19-05-2017 18:11:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('81', '3', '0.3.', '门锁对码管理', 81, '/../findCode.do', null, null, '1', null, '1', to_timestamp('01-06-2017 15:52:12.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('01-06-2017 15:52:12.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('2', '0', '0,', '房源管理', 2, null, null, null, '1', null, '1', to_timestamp('07-05-2017 06:45:22.511000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('07-05-2017 06:45:22.511000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('3', '0', '0,', '租户管理', 3, null, null, null, '1', null, '1', to_timestamp('07-05-2017 06:45:22.531000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('07-05-2017 06:45:22.531000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('62', '3', '0.3.', '业务记录管理', 62, '/../findCheckInRecord.do', null, null, '1', null, '1', to_timestamp('21-05-2017 11:43:37.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('21-05-2017 11:43:37.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('5', '0', '0,', '档案管理', 5, null, null, null, '1', null, '1', to_timestamp('07-05-2017 06:45:22.598000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('07-05-2017 06:45:22.598000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('6', '0', '0,', '解约退租管理', 6, null, null, null, '1', null, '1', to_timestamp('07-05-2017 06:45:22.626000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('07-05-2017 06:45:22.626000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('63', '3', '0.3.', '续租管理', 63, '/../findReletPaged.do', null, null, '1', null, '1', to_timestamp('21-05-2017 11:43:43.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('21-05-2017 11:43:43.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('1', '0', '0,1,', '台账', -1, null, null, null, '1', null, '1', to_timestamp('04-06-2017 08:43:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('04-06-2017 08:43:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('66', '1', '0,65,', '台账', 66, '/../ledger', null, null, '1', null, '1', to_timestamp('04-06-2017 08:43:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('04-06-2017 08:43:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('8', '0', '0,', '报修管理', 8, null, null, null, '1', null, '1', to_timestamp('07-05-2017 06:45:22.737000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('07-05-2017 06:45:22.737000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('11', '8', '0,8', '报修登记', 11, '/sys/rent/list/', null, null, '1', null, '1', to_timestamp('07-05-2017 06:45:22.754000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('07-05-2017 06:45:22.754000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('12', '8', '0,8,', '现场查验', 12, '/sys/repaire/check', null, null, '1', null, '1', to_timestamp('07-05-2017 06:45:22.771000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('07-05-2017 06:45:22.771000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('13', '8', '0,8,', '报修管理', 13, '/sys/repaire/manage', null, null, '1', null, '1', to_timestamp('07-05-2017 06:45:22.783000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('07-05-2017 06:45:22.783000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
insert into RENT_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('15', '3', '0,1,', '入住受理', 15, '/../prHouseDrawing', null, null, '1', null, '1', to_timestamp('09-05-2017 08:43:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('09-05-2017 08:43:54.524000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0');
commit;
prompt 30 records loaded
prompt Loading ROLE...
insert into ROLE (id, name, modules, createuser, createtime, updateuser, updatetime)
values (8, '第三方维修', '62-63-64-61', null, null, null, null);
insert into ROLE (id, name, modules, createuser, createtime, updateuser, updatetime)
values (1, '管理员', null, null, null, null, null);
commit;
prompt 2 records loaded
prompt Loading USERS...
insert into USERS (id, name, pwd, sex, phone, address, estate_id, roles, createuser, createtime, updateuser, updatetime, del)
values (1, 'admin', '49ba59abbe56e057', null, null, null, null, '1', null, null, null, null, 0);
commit;
prompt 1 records loaded
prompt Enabling foreign key constraints for USERS...
alter table USERS enable constraint SYS_C005239;
prompt Enabling triggers for ESTATE_TYPE...
alter table ESTATE_TYPE enable all triggers;
prompt Enabling triggers for MODULE...
alter table MODULE enable all triggers;
prompt Enabling triggers for PRH_PAYCODE...
alter table PRH_PAYCODE enable all triggers;
prompt Enabling triggers for RENT_MENU...
alter table RENT_MENU enable all triggers;
prompt Enabling triggers for ROLE...
alter table ROLE enable all triggers;
prompt Enabling triggers for USERS...
alter table USERS enable all triggers;
