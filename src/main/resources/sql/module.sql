prompt Disabling triggers for MODULE...
alter table MODULE disable all triggers;
prompt Deleting MODULE...
delete from MODULE;
commit;
prompt Loading MODULE...
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (11, ''业务记录管理'', 62, 3, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (12, ''续租管理'', 63, 3, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (13, ''门锁对码管理'', 81, 3, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (14, ''打印合同'', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (16, ''修改'', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (17, ''删除'', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (67, ''电子附件'', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (68, ''下载'', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (69, ''删除附件'', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (70, ''上传'', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (76, ''打印合同'', null, 12, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (81, ''查看租户信息'', null, 11, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (2, ''房源管理'', 2, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (3, ''租户管理'', 3, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (4, ''解约退租管理'', 6, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (5, ''档案管理'', 5, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (6, ''财务收费'', 34, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (7, ''台账'', 1, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (8, ''报修管理'', 8, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (9, ''房源信息'', 32, 2, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (100, ''新增'', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (18, ''续租申请'', null, 12, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (20, ''修改'', null, 12, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (21, ''删除'', null, 12, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (22, ''门卡管理'', null, 13, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (23, ''开卡'', null, 13, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (24, ''生成身份证对码'', null, 13, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (25, ''挂失卡'', null, 13, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (41, ''单位租户明细'', null, 26, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (42, ''查看房间历史租户'', null, 27, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (43, ''查看配套设备'', null, 27, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (75, ''历史身份证对码'', null, 13, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (45, ''批量添加设备(模板)'', null, 27, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (46, ''结算'', null, 29, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (47, ''核算'', null, 30, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (48, ''退租结算'', null, 31, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (49, ''复选调价'', null, 32, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (50, ''批量调价'', null, 32, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (51, ''报修登记'', 11, 8, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (52, ''现场查验'', 12, 8, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (53, ''报修管理'', 13, 8, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (54, ''报修申请'', null, 51, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (55, ''现场查验'', null, 52, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (56, ''打印单据'', null, 52, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (57, ''提交维修方'', null, 53, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (58, ''打印交办单'', null, 53, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (59, ''验收'', null, 53, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (60, ''打印单据'', null, 53, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (61, ''第三方维修'', null, -1, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (62, ''第三方维修'', null, 61, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (63, ''维修中'', null, 62, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (64, ''维修完成'', null, 62, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (74, ''身份证挂失确认'', null, 13, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (78, ''日报表'', 102, 77, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (79, ''月报表'', 103, 77, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (80, ''租户统计'', 104, 77, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (77, ''报表统计'', 101, 0, 1, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (1, ''全部'', null, null, 0, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (10, ''入住受理'', 15, 3, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (34, ''退租/验房'', 33, 4, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (35, ''申请退租'', null, 34, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (36, ''确认退租'', null, 34, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (37, ''打印单据'', null, 34, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (38, ''报修申请'', null, 34, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (39, ''修改'', null, 34, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (40, ''删除'', null, 34, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (71, ''写设房号卡'', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (72, ''写锁号设置卡'', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (26, ''单位档案'', 16, 5, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (27, ''房源档案'', 17, 5, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (28, ''租户档案'', 18, 5, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (29, ''日常合同扣租'', 35, 6, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (30, ''租金核算'', 36, 6, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (31, ''退租结算清账'', 37, 6, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (32, ''租金调整审核'', 38, 6, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (33, ''台账'', 66, 7, 2, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (101, ''修改'', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (102, ''删除'', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (103, ''批量导入'', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (104, ''锁定'', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (105, ''解锁'', null, 9, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (73, ''白卡查询'', null, 13, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (65, ''入住登记'', null, 10, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (66, ''租户信息查看'', null, 10, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (107, ''作废'', null, 29, 3, null, null, null, null);
insert into MODULE (id, name, menu_id, pid, levels, createuser, createtime, updateuser, updatetime)
values (108, ''打印对码'', null, 13, 3, null, null, null, null);
commit;
prompt 86 records loaded
prompt Enabling triggers for MODULE...
alter table MODULE enable all triggers;
