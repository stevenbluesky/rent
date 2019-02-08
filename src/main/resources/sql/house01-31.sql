prompt
prompt Creating table PROFILE
prompt ======================
prompt
create table HOUSE.PROFILE
(
  guestno    VARCHAR2(100) not null,
  class      CHAR(1) default 'G',
  sta        CHAR(1) default 'I',
  sno        VARCHAR2(25) default '',
  cno        VARCHAR2(20) default '',
  name       VARCHAR2(50),
  fname      VARCHAR2(30) default '',
  lname      VARCHAR2(30) default '',
  name2      VARCHAR2(50) default '',
  name3      VARCHAR2(50) default '',
  src        VARCHAR2(3) default '',
  mkt        VARCHAR2(3) default '',
  vip        CHAR(1) default '0',
  sex        VARCHAR2(10) default '1',
  lang       CHAR(1) default 'C',
  title      VARCHAR2(10) default '',
  birth      DATE,
  race       CHAR(2) default '',
  religion   CHAR(2) default '',
  education  CHAR(2) default '',
  occupation CHAR(2) default '',
  nation     CHAR(20) default 'CN',
  idcls      CHAR(3) default '',
  idno       VARCHAR2(20) default '',
  idend      DATE,
  visaid     CHAR(1),
  visaend    DATE,
  visano     VARCHAR2(20),
  visaunit   CHAR(4),
  rjplace    CHAR(3),
  rjdate     DATE,
  cusno      VARCHAR2(30) default '',
  company    VARCHAR2(60) default '',
  saleid     VARCHAR2(10) default '',
  liason     VARCHAR2(20) default '',
  liason1    VARCHAR2(30) default '',
  country    CHAR(3) default 'CN',
  state      CHAR(3) default '',
  city       CHAR(6) default '',
  street     VARCHAR2(200) default '',
  zip        VARCHAR2(6) default '',
  mobile     VARCHAR2(20) default '',
  phone      VARCHAR2(20) default '',
  fax        VARCHAR2(20) default '',
  wetsite    VARCHAR2(30) default '',
  email      VARCHAR2(30) default '',
  country1   CHAR(3) default 'CN',
  state1     CHAR(3) default '',
  city1      CHAR(6) default '',
  street1    VARCHAR2(50) default '',
  zip1       VARCHAR2(6) default '',
  mobile1    VARCHAR2(20) default '',
  phone1     VARCHAR2(20) default '',
  fax1       VARCHAR2(20) default '',
  email1     VARCHAR2(30) default '',
  bank       VARCHAR2(3),
  bankno     VARCHAR2(20),
  refer      VARCHAR2(100),
  fancy1     VARCHAR2(100),
  fancy2     VARCHAR2(100),
  fancy3     VARCHAR2(100),
  remark     VARCHAR2(200),
  class1     VARCHAR2(3) default '0',
  class2     VARCHAR2(3) default '0',
  class3     VARCHAR2(3) default '0',
  code1      VARCHAR2(10),
  code2      VARCHAR2(10),
  code3      VARCHAR2(10),
  crtid      VARCHAR2(10),
  crtdate    DATE default sysdate,
  logid      VARCHAR2(10),
  logdate    DATE default sysdate,
  logmark    NUMBER(8) default 0,
  orgid      VARCHAR2(3) default '000',
  logupdate  DATE,
  estate_id  NUMBER(8)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 192K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.PROFILE
  add primary key (GUESTNO)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ROOM_TYPE
prompt ========================
prompt
create table HOUSE.ROOM_TYPE
(
  id       NUMBER(8) not null,
  no       VARCHAR2(50),
  name     VARCHAR2(50) not null,
  descript VARCHAR2(50),
  remark   VARCHAR2(50),
  now_date DATE
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.ROOM_TYPE
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ESTATE_TYPE
prompt ==========================
prompt
create table HOUSE.ESTATE_TYPE
(
  id   NUMBER(8) not null,
  name VARCHAR2(50)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.ESTATE_TYPE
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ESTATE
prompt =====================
prompt
create table HOUSE.ESTATE
(
  id          NUMBER(8) not null,
  name        VARCHAR2(50),
  type_id     NUMBER(8),
  address     VARCHAR2(50),
  remark      VARCHAR2(100),
  price       NUMBER(8,2),
  author_code VARCHAR2(30),
  phone       VARCHAR2(20),
  map_pic     VARCHAR2(200)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.ESTATE
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.ESTATE
  add foreign key (TYPE_ID)
  references HOUSE.ESTATE_TYPE (ID);

prompt
prompt Creating table BUILDING_FLOOR
prompt =============================
prompt
create table HOUSE.BUILDING_FLOOR
(
  id           NUMBER(8) not null,
  name         VARCHAR2(50),
  english_name VARCHAR2(50),
  remark       VARCHAR2(50)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 384K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.BUILDING_FLOOR
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 192K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BUILDING_NO
prompt ==========================
prompt
create table HOUSE.BUILDING_NO
(
  id          VARCHAR2(50) not null,
  name        VARCHAR2(50),
  building_id VARCHAR2(50),
  descript    VARCHAR2(50),
  estate_id   NUMBER(8),
  month_pay   NUMBER(8,2),
  remark      VARCHAR2(50),
  buildingid  VARCHAR2(20)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.BUILDING_NO
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.BUILDING_NO
  add foreign key (ESTATE_ID)
  references HOUSE.ESTATE (ID);

prompt
prompt Creating table PR_HOUSE
prompt =======================
prompt
create table HOUSE.PR_HOUSE
(
  id                NUMBER(8) not null,
  no                VARCHAR2(50),
  estate_id         NUMBER(8),
  building_no_id    VARCHAR2(50),
  room_no           VARCHAR2(50),
  room_no_id        NUMBER(8),
  building_floor_id NUMBER(8),
  room_type_id      NUMBER(8),
  area              NUMBER(8,2),
  room_num          NUMBER(8),
  price             NUMBER(8,2),
  month_price       NUMBER(8,2),
  back_price        NUMBER(8,2),
  cheap_price       NUMBER(8,3),
  state             NUMBER(2),
  remark            VARCHAR2(100),
  master_id         NUMBER(8),
  house_code        CHAR(10),
  rentmod           NUMBER(8,2),
  moddate           DATE,
  decdate           DATE,
  decman            VARCHAR2(50),
  reason            VARCHAR2(100),
  createuser        VARCHAR2(30),
  createtime        DATE,
  updateuser        VARCHAR2(30),
  updatetime        DATE,
  originstate       NUMBER(2),
  is_tui            NVARCHAR2(2)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 448K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.PR_HOUSE
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.PR_HOUSE
  add foreign key (ROOM_TYPE_ID)
  references HOUSE.ROOM_TYPE (ID);
alter table HOUSE.PR_HOUSE
  add foreign key (ESTATE_ID)
  references HOUSE.ESTATE (ID);
alter table HOUSE.PR_HOUSE
  add foreign key (BUILDING_NO_ID)
  references HOUSE.BUILDING_NO (ID);
alter table HOUSE.PR_HOUSE
  add foreign key (BUILDING_FLOOR_ID)
  references HOUSE.BUILDING_FLOOR (ID);

prompt
prompt Creating table RENT_PAY_WAY
prompt ===========================
prompt
create table HOUSE.RENT_PAY_WAY
(
  id           NUMBER(8) not null,
  name         VARCHAR2(50),
  deposit_pay  NUMBER(8),
  rent_pay     NUMBER(8),
  "mode"       VARCHAR2(50),
  day_or_month NUMBER(8),
  descript1    VARCHAR2(100),
  descript2    VARCHAR2(100),
  descript3    VARCHAR2(100)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.RENT_PAY_WAY
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SUBSIDY_TYPE
prompt ===========================
prompt
create table HOUSE.SUBSIDY_TYPE
(
  id   NUMBER(8) not null,
  name VARCHAR2(50)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.SUBSIDY_TYPE
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRH_MASTER
prompt =========================
prompt
create table HOUSE.PRH_MASTER
(
  id              NUMBER(8) not null,
  org_id          VARCHAR2(3),
  guest_no        VARCHAR2(100),
  house_id        NUMBER(8),
  sta             CHAR(1),
  type            VARCHAR2(10),
  src             VARCHAR2(3),
  file_no         VARCHAR2(20),
  bdate           DATE,
  edate           DATE,
  rate            NUMBER(8,2) default 0,
  setrate         NUMBER(8,2) default 0,
  reason          VARCHAR2(50),
  numbs           NUMBER(8) default 1,
  arrtime         DATE,
  dep             DATE,
  arr1            DATE,
  dep1            DATE,
  deposit         NUMBER(8,2) default 0,
  rent_code       NUMBER(8),
  rent_date       DATE,
  remark          VARCHAR2(100),
  payment         NUMBER(8,2) default 0,
  expend          NUMBER(8,2) default 0,
  crt             VARCHAR2(50),
  crttime         DATE,
  res             VARCHAR2(10),
  restime         DATE,
  con             VARCHAR2(50),
  contime         DATE,
  ci              VARCHAR2(50),
  citime          DATE,
  co              VARCHAR2(50),
  cotime          DATE,
  logid           VARCHAR2(10),
  log_name        VARCHAR2(20),
  log_date        DATE default sysdate,
  log_mark        NUMBER(8) default 0,
  bank_flag       CHAR(1) default 'F',
  bank            VARCHAR2(5) default '',
  bank_no         VARCHAR2(20) default '',
  dep_flag        CHAR(1) default 'F',
  dep_apply       DATE,
  dep_file_no     VARCHAR2(20),
  rel_flag        CHAR(1) default 'F',
  relid           VARCHAR2(10),
  rel_date        DATE,
  refer1          VARCHAR2(100),
  refer2          VARCHAR2(100),
  refer3          VARCHAR2(100),
  refer4          VARCHAR2(100),
  bdate2          DATE,
  cusno           VARCHAR2(50) default '',
  prcode          CHAR(1) default 'F',
  pdcode          CHAR(1) default 'F',
  log_update      DATE,
  bank_ref        VARCHAR2(80) default '',
  tag1            VARCHAR2(30) default '',
  tag2            VARCHAR2(30) default '',
  tag3            VARCHAR2(30) default '',
  tag4            VARCHAR2(30) default '',
  pic_identity    VARCHAR2(200),
  pic_photo       VARCHAR2(200),
  physics_card_id VARCHAR2(50),
  door_lock       VARCHAR2(50),
  update_time     DATE,
  update_user     NUMBER(8),
  subsidy_type_id NUMBER(8),
  old_master_id   NUMBER(8),
  new_master_id   NUMBER(8),
  createuser      VARCHAR2(30),
  createtime      DATE,
  tuimoney        NUMBER(8,2),
  tuidate         DATE,
  tuidesposit     NUMBER(8,2),
  collect_money   NUMBER(8,2),
  batch           VARCHAR2(50)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.PRH_MASTER
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.PRH_MASTER
  add foreign key (GUEST_NO)
  references HOUSE.PROFILE (GUESTNO);
alter table HOUSE.PRH_MASTER
  add foreign key (SUBSIDY_TYPE_ID)
  references HOUSE.SUBSIDY_TYPE (ID);
alter table HOUSE.PRH_MASTER
  add foreign key (RENT_CODE)
  references HOUSE.RENT_PAY_WAY (ID);
alter table HOUSE.PRH_MASTER
  add foreign key (HOUSE_ID)
  references HOUSE.PR_HOUSE (ID);

prompt
prompt Creating table ATTACHMENT
prompt =========================
prompt
create table HOUSE.ATTACHMENT
(
  id          NUMBER(8) not null,
  name        VARCHAR2(100),
  type        NUMBER(1),
  master_id   NUMBER(8) not null,
  upload_time DATE,
  remark      VARCHAR2(50)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 192K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.ATTACHMENT
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.ATTACHMENT
  add foreign key (MASTER_ID)
  references HOUSE.PRH_MASTER (ID);

prompt
prompt Creating table BUILDING
prompt =======================
prompt
create table HOUSE.BUILDING
(
  id             VARCHAR2(50) not null,
  name           VARCHAR2(50),
  buildingnocode VARCHAR2(500),
  estate_id      NUMBER(8),
  remark         VARCHAR2(50),
  position_x     NUMBER(8),
  position_y     NUMBER(8)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.BUILDING
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.BUILDING
  add foreign key (ESTATE_ID)
  references HOUSE.ESTATE (ID);

prompt
prompt Creating table CARD
prompt ===================
prompt
create table HOUSE.CARD
(
  id          NUMBER(8) not null,
  master_id   NUMBER(8),
  link_id     NUMBER(8),
  state       NUMBER(8),
  createuser  VARCHAR2(30),
  createtime  DATE,
  updateuser  VARCHAR2(30),
  updatetime  DATE,
  type        NUMBER(8),
  iden_pwd    VARCHAR2(50),
  old_card_id NUMBER(8)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.CARD
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table EQUIPMENT
prompt ========================
prompt
create table HOUSE.EQUIPMENT
(
  id    NUMBER,
  name  VARCHAR2(30),
  price NUMBER,
  type  NUMBER
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table HISTORY_IDEN_PWD
prompt ===============================
prompt
create table HOUSE.HISTORY_IDEN_PWD
(
  id          NUMBER(8) not null,
  master_id   NUMBER(8) not null,
  link_id     NUMBER(8),
  iden_pwd    VARCHAR2(50),
  create_user NUMBER(8),
  create_time DATE
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.HISTORY_IDEN_PWD
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table HOUSE_SPACE
prompt ==========================
prompt
create table HOUSE.HOUSE_SPACE
(
  id         NUMBER(8) not null,
  house_id   NUMBER(8) not null,
  begin_date DATE,
  end_date   DATE
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.HOUSE_SPACE
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.HOUSE_SPACE
  add foreign key (HOUSE_ID)
  references HOUSE.PR_HOUSE (ID);

prompt
prompt Creating table MODULE
prompt =====================
prompt
create table HOUSE.MODULE
(
  id         NUMBER(8) not null,
  name       VARCHAR2(20),
  menu_id    NUMBER(8),
  pid        NUMBER(8),
  levels     NUMBER(8),
  createuser VARCHAR2(30),
  createtime DATE,
  updateuser VARCHAR2(30),
  updatetime DATE
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.MODULE
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PAYMENT_CODE
prompt ===========================
prompt
create table HOUSE.PAYMENT_CODE
(
  id      NUMBER(8) not null,
  name    VARCHAR2(50),
  "group" VARCHAR2(50),
  is_pay  VARCHAR2(50),
  pay     NUMBER(8,2)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.PAYMENT_CODE
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRH_LINKMAN
prompt ==========================
prompt
create table HOUSE.PRH_LINKMAN
(
  id              NUMBER(8) not null,
  master_id       NUMBER(8),
  guestno         VARCHAR2(100),
  flag            VARCHAR2(1) default 'F',
  link            VARCHAR2(10),
  arrdate         DATE default sysdate,
  refer           VARCHAR2(50),
  sta             CHAR(1),
  depdate         DATE,
  arrfileno       VARCHAR2(20),
  depfileno       VARCHAR2(20),
  home            NUMBER(8) default 0,
  homes           NUMBER(8) default 1,
  subsidy_type_id NUMBER(8),
  del             NUMBER(1),
  addtime         DATE,
  deltime         DATE,
  photo           VARCHAR2(200)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.PRH_LINKMAN
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRH_PAYCODE
prompt ==========================
prompt
create table HOUSE.PRH_PAYCODE
(
  id   NUMBER(8) not null,
  name VARCHAR2(20)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRH_PAYMENT
prompt ==========================
prompt
create table HOUSE.PRH_PAYMENT
(
  id        NUMBER(8) not null,
  accnt     VARCHAR2(10),
  inumber   NUMBER(8),
  orgid     VARCHAR2(3),
  prtno     VARCHAR2(10) not null,
  paycode   VARCHAR2(10) default '',
  ref       VARCHAR2(20),
  credit    NUMBER(8,2) default 0 not null,
  ref1      VARCHAR2(50) default '',
  ref2      VARCHAR2(50) default '',
  logid     VARCHAR2(10),
  logshift  VARCHAR2(1),
  logname   VARCHAR2(20),
  logdate   DATE default sysdate not null,
  "sysdate" DATE not null,
  roomno    VARCHAR2(10),
  oroomno   VARCHAR2(12),
  credit1   NUMBER(8,2) default 0,
  tag       VARCHAR2(3),
  logupdate DATE
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 256K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.PRH_PAYMENT
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRH_RENTAL
prompt =========================
prompt
create table HOUSE.PRH_RENTAL
(
  id         NUMBER(8) not null,
  accnt      NUMBER(8) not null,
  inumber    NUMBER(8),
  flag       CHAR(1) default 'I',
  orgid      VARCHAR2(3),
  prtno      VARCHAR2(10),
  name       VARCHAR2(100),
  guestno    VARCHAR2(10),
  roomno     VARCHAR2(10),
  vroomno    VARCHAR2(10),
  rmtype     VARCHAR2(10),
  rate       NUMBER(8,2),
  fileno     VARCHAR2(20),
  deposit    NUMBER(8,2) default 0,
  charge     NUMBER(8,2) default 0,
  charge1    NUMBER(8,2) default 0,
  charge2    NUMBER(8,2) default 0,
  charge3    NUMBER(8,2) default 0,
  charge4    NUMBER(8,2) default 0,
  charge5    NUMBER(8,2) default 0,
  charge6    NUMBER(8,2) default 0,
  charge7    NUMBER(8,2) default 0,
  charge8    NUMBER(8,2) default 0,
  charge9    NUMBER(8,2) default 0,
  bdate      DATE,
  edate      DATE,
  amount     NUMBER(8,2) default 0,
  ref1       VARCHAR2(50),
  ref2       VARCHAR2(50),
  ref3       VARCHAR2(50),
  ref4       VARCHAR2(50),
  ref5       VARCHAR2(50),
  prtid      VARCHAR2(10) default '',
  prtname    VARCHAR2(20),
  prtdate    DATE,
  prttimes   NUMBER(8) default 0,
  sta        CHAR(1) default 'F',
  cashier    VARCHAR2(10) default '',
  cashname   VARCHAR2(20),
  cashdate   DATE,
  "sysdate"  DATE,
  audit1     VARCHAR2(1) default 'F',
  logid1     VARCHAR2(10),
  audit1time DATE,
  audit2     VARCHAR2(1) default 'F',
  logid2     VARCHAR2(10),
  audittime  DATE,
  iflag      CHAR(1) default 'F',
  iamount    NUMBER(8,2) default 0,
  ino        VARCHAR2(20) default '',
  ilogid     VARCHAR2(10) default '',
  ilogname   VARCHAR2(20) default '',
  isysdate   DATE,
  ilogdate   DATE,
  method     VARCHAR2(1) default 'A',
  oroomno    VARCHAR2(12),
  days       NUMBER(8) default 0,
  fmonths    NUMBER(8) default 0,
  fdays      NUMBER(8) default 0,
  famount    NUMBER(8,2) default 0,
  cusno      VARCHAR2(10),
  logupdate  DATE
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 576K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.PRH_RENTAL
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.PRH_RENTAL
  add foreign key (ACCNT)
  references HOUSE.PRH_MASTER (ID);

prompt
prompt Creating table PRH_TEMP_LIVE_MAN
prompt ================================
prompt
create table HOUSE.PRH_TEMP_LIVE_MAN
(
  id        NUMBER not null,
  master_id NUMBER,
  guestno   VARCHAR2(50),
  bdate     DATE,
  edate     DATE,
  del       NUMBER,
  iden_pwd  VARCHAR2(50)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.PRH_TEMP_LIVE_MAN
  add constraint ID primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table RENT_MENU
prompt ========================
prompt
create table HOUSE.RENT_MENU
(
  id          VARCHAR2(64) not null,
  parent_id   VARCHAR2(64) not null,
  parent_ids  VARCHAR2(2000) not null,
  name        NVARCHAR2(100) not null,
  sort        NUMBER(10) not null,
  href        VARCHAR2(2000),
  target      VARCHAR2(20),
  icon        VARCHAR2(100),
  is_show     CHAR(1) not null,
  permission  VARCHAR2(200),
  create_by   VARCHAR2(64) not null,
  create_date TIMESTAMP(6) not null,
  update_by   VARCHAR2(64) not null,
  update_date TIMESTAMP(6) not null,
  remarks     NVARCHAR2(255),
  del_flag    CHAR(1) default '0' not null
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.RENT_MENU
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table REPAIRE
prompt ======================
prompt
create table HOUSE.REPAIRE
(
  id              VARCHAR2(64) not null,
  address         VARCHAR2(500),
  room_num        VARCHAR2(10),
  room_type       VARCHAR2(100),
  room_size       VARCHAR2(45),
  repairer        VARCHAR2(45),
  repairer_phone  VARCHAR2(45),
  repairer_time   TIMESTAMP(6),
  repaire_type    VARCHAR2(45),
  comments        VARCHAR2(500),
  approval_status VARCHAR2(45),
  status          VARCHAR2(45),
  isdelete        VARCHAR2(5) default 'false',
  floor           VARCHAR2(5),
  checker         VARCHAR2(45),
  work            VARCHAR2(45),
  equipment       VARCHAR2(45),
  image           VARCHAR2(500),
  payment         VARCHAR2(10),
  start_time      DATE,
  end_time        DATE,
  applier         VARCHAR2(45),
  third_com       VARCHAR2(100),
  validate_time   DATE,
  masterid        NUMBER(8),
  houseid         NUMBER(8)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.REPAIRE
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table REPAIRE_PAYMENT
prompt ==============================
prompt
create table HOUSE.REPAIRE_PAYMENT
(
  id        VARCHAR2(50) not null,
  project   VARCHAR2(20),
  count     NUMBER(8,2),
  price     VARCHAR2(10),
  repaireid VARCHAR2(50)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.REPAIRE_PAYMENT
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table RMDEV_TEMP
prompt =========================
prompt
create table HOUSE.RMDEV_TEMP
(
  id       NUMBER,
  roomtype VARCHAR2(50),
  cla      CHAR(30),
  eqid     NUMBER,
  num      NUMBER
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ROLE
prompt ===================
prompt
create table HOUSE.ROLE
(
  id         NUMBER(8) not null,
  name       VARCHAR2(30),
  modules    VARCHAR2(1000),
  createuser VARCHAR2(30),
  createtime DATE,
  updateuser VARCHAR2(30),
  updatetime DATE
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column HOUSE.ROLE.id
  is 'id';
comment on column HOUSE.ROLE.name
  is '角色名';
comment on column HOUSE.ROLE.modules
  is '模块集合字符串';
alter table HOUSE.ROLE
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SUBSIDY
prompt ======================
prompt
create table HOUSE.SUBSIDY
(
  id             NUMBER(8) not null,
  begin_date     DATE,
  end_date       DATE,
  per_one_area   NUMBER(8),
  min_safe_area  NUMBER(8),
  max_safe_area  NUMBER(8),
  max_area       NUMBER(8),
  price          NUMBER(8,2),
  in_percent_id  NUMBER(8),
  out_percent_id NUMBER(8),
  estate_id      NUMBER(8),
  update_time    DATE,
  update_user    NUMBER(8)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.SUBSIDY
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.SUBSIDY
  add foreign key (ESTATE_ID)
  references HOUSE.ESTATE (ID);

prompt
prompt Creating table SUBSIDY_PERCENT
prompt ==============================
prompt
create table HOUSE.SUBSIDY_PERCENT
(
  id             NUMBER(8) not null,
  in_low_object  NUMBER(8,2),
  in_low         NUMBER(8,2),
  in_middle      NUMBER(8,2),
  out_low_object NUMBER(8,2),
  out_low        NUMBER(8,2),
  out_middle     NUMBER(8,2),
  update_time    NUMBER(8,2),
  update_user    NUMBER(8,2)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.SUBSIDY_PERCENT
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SUBSIDY_WITH_TYPE
prompt ================================
prompt
create table HOUSE.SUBSIDY_WITH_TYPE
(
  id         NUMBER(8) not null,
  subsidy_id NUMBER(8),
  type_id    NUMBER(8),
  in_or_out  NUMBER(8),
  percent    NUMBER(8,4)
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.SUBSIDY_WITH_TYPE
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.SUBSIDY_WITH_TYPE
  add constraint SUBSIDY_F foreign key (SUBSIDY_ID)
  references HOUSE.SUBSIDY (ID);
alter table HOUSE.SUBSIDY_WITH_TYPE
  add constraint TYPE_F foreign key (TYPE_ID)
  references HOUSE.SUBSIDY_TYPE (ID);

prompt
prompt Creating table USERS
prompt ====================
prompt
create table HOUSE.USERS
(
  id         NUMBER(8) not null,
  name       VARCHAR2(30),
  pwd        VARCHAR2(30),
  sex        NUMBER(1),
  phone      VARCHAR2(30),
  address    VARCHAR2(30),
  estate_id  NUMBER(8),
  roles      VARCHAR2(100),
  createuser VARCHAR2(30),
  createtime DATE,
  updateuser VARCHAR2(30),
  updatetime DATE,
  del        NUMBER
)
tablespace HOUSE_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column HOUSE.USERS.id
  is 'id';
comment on column HOUSE.USERS.name
  is '用户名';
comment on column HOUSE.USERS.pwd
  is '密码';
comment on column HOUSE.USERS.sex
  is '性别';
comment on column HOUSE.USERS.phone
  is '电话';
comment on column HOUSE.USERS.address
  is '地址';
comment on column HOUSE.USERS.estate_id
  is '物业id';
comment on column HOUSE.USERS.roles
  is '角色集合字符串';
comment on column HOUSE.USERS.del
  is '是否被删除';
alter table HOUSE.USERS
  add primary key (ID)
  using index 
  tablespace HOUSE_SPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table HOUSE.USERS
  add foreign key (ESTATE_ID)
  references HOUSE.ESTATE (ID);

prompt
prompt Creating sequence EMP_SEQUENCE
prompt ==============================
prompt
create sequence HOUSE.EMP_SEQUENCE
minvalue 1
maxvalue 9999999999999999999999999999
start with 10000000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ACCNT_SELECTED
prompt ====================================
prompt
create sequence HOUSE.SEQ_ACCNT_SELECTED
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ADDRESS
prompt =============================
prompt
create sequence HOUSE.SEQ_ADDRESS
minvalue 1
maxvalue 9999999999999999999999999999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ATTACHMENT
prompt ================================
prompt
create sequence HOUSE.SEQ_ATTACHMENT
minvalue 1
maxvalue 9999999999999999999999999999
start with 2016
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_AUTHORIZE
prompt ===============================
prompt
create sequence HOUSE.SEQ_AUTHORIZE
minvalue 1
maxvalue 9999999999999999999999999999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_BILL_BILL_UNIT
prompt ====================================
prompt
create sequence HOUSE.SEQ_BILL_BILL_UNIT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_BILL_DATA
prompt ===============================
prompt
create sequence HOUSE.SEQ_BILL_DATA
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_BILL_DEFAULT
prompt ==================================
prompt
create sequence HOUSE.SEQ_BILL_DEFAULT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_BILL_MODE
prompt ===============================
prompt
create sequence HOUSE.SEQ_BILL_MODE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_BILL_PCPRINT
prompt ==================================
prompt
create sequence HOUSE.SEQ_BILL_PCPRINT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_BUILDING
prompt ==============================
prompt
create sequence HOUSE.SEQ_BUILDING
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_BUILDING_FLOOR
prompt ====================================
prompt
create sequence HOUSE.SEQ_BUILDING_FLOOR
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_CARD
prompt ==========================
prompt
create sequence HOUSE.SEQ_CARD
minvalue 1
maxvalue 9999999999999999999999999999
start with 10007402
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_COST_CODE
prompt ===============================
prompt
create sequence HOUSE.SEQ_COST_CODE
minvalue 1
maxvalue 9999999999999999999999999999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_DOOR_PARM
prompt ===============================
prompt
create sequence HOUSE.SEQ_DOOR_PARM
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_DOOR_RECORD
prompt =================================
prompt
create sequence HOUSE.SEQ_DOOR_RECORD
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_DOOR_ROOMNO
prompt =================================
prompt
create sequence HOUSE.SEQ_DOOR_ROOMNO
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ELECTRIC
prompt ==============================
prompt
create sequence HOUSE.SEQ_ELECTRIC
minvalue 1
maxvalue 9999999999999999999999999999
start with 61
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_EQUIPMENT
prompt ===============================
prompt
create sequence HOUSE.SEQ_EQUIPMENT
minvalue 1
maxvalue 9999999999999999999999999999
start with 61
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ESTATE
prompt ============================
prompt
create sequence HOUSE.SEQ_ESTATE
minvalue 1
maxvalue 9999999999999999999999999999
start with 322
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ESTATE_PROJECT
prompt ====================================
prompt
create sequence HOUSE.SEQ_ESTATE_PROJECT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ESTATE_TYPE
prompt =================================
prompt
create sequence HOUSE.SEQ_ESTATE_TYPE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_HISTORY_IDEN_PWD
prompt ======================================
prompt
create sequence HOUSE.SEQ_HISTORY_IDEN_PWD
minvalue 1
maxvalue 9999999999999999999999999999
start with 104
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_HOUSE_SPACE
prompt =================================
prompt
create sequence HOUSE.SEQ_HOUSE_SPACE
minvalue 1
maxvalue 9999999999999999999999999999
start with 300
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_MAIL
prompt ==========================
prompt
create sequence HOUSE.SEQ_MAIL
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_MODULE
prompt ============================
prompt
create sequence HOUSE.SEQ_MODULE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PAYMENT_CODE
prompt ==================================
prompt
create sequence HOUSE.SEQ_PAYMENT_CODE
minvalue 1
maxvalue 9999999999999999999999999999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PBCATCOL
prompt ==============================
prompt
create sequence HOUSE.SEQ_PBCATCOL
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PBCATEDT
prompt ==============================
prompt
create sequence HOUSE.SEQ_PBCATEDT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PBCATFMT
prompt ==============================
prompt
create sequence HOUSE.SEQ_PBCATFMT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PBCATTBL
prompt ==============================
prompt
create sequence HOUSE.SEQ_PBCATTBL
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PBCATVLD
prompt ==============================
prompt
create sequence HOUSE.SEQ_PBCATVLD
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PICTURE
prompt =============================
prompt
create sequence HOUSE.SEQ_PICTURE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_BAL
prompt =============================
prompt
create sequence HOUSE.SEQ_PRH_BAL
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_BAL2
prompt ==============================
prompt
create sequence HOUSE.SEQ_PRH_BAL2
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_BAL22
prompt ===============================
prompt
create sequence HOUSE.SEQ_PRH_BAL22
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_BAL_DEF
prompt =================================
prompt
create sequence HOUSE.SEQ_PRH_BAL_DEF
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_CHECKOUT
prompt ==================================
prompt
create sequence HOUSE.SEQ_PRH_CHECKOUT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_CODE
prompt ==============================
prompt
create sequence HOUSE.SEQ_PRH_CODE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_DEV_TMP2
prompt ==================================
prompt
create sequence HOUSE.SEQ_PRH_DEV_TMP2
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_DEV_TMP3
prompt ==================================
prompt
create sequence HOUSE.SEQ_PRH_DEV_TMP3
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_FREE
prompt ==============================
prompt
create sequence HOUSE.SEQ_PRH_FREE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_IMP
prompt =============================
prompt
create sequence HOUSE.SEQ_PRH_IMP
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_LINKMAN
prompt =================================
prompt
create sequence HOUSE.SEQ_PRH_LINKMAN
minvalue 1
maxvalue 9999999999999999999999999999
start with 1524
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_MASTER
prompt ================================
prompt
create sequence HOUSE.SEQ_PRH_MASTER
minvalue 1
maxvalue 9999999999999999999999999999
start with 1727
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_MASTER2
prompt =================================
prompt
create sequence HOUSE.SEQ_PRH_MASTER2
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_MASTER_ADD
prompt ====================================
prompt
create sequence HOUSE.SEQ_PRH_MASTER_ADD
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_MASTER_ADDTL
prompt ======================================
prompt
create sequence HOUSE.SEQ_PRH_MASTER_ADDTL
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_MASTER_PRINT
prompt ======================================
prompt
create sequence HOUSE.SEQ_PRH_MASTER_PRINT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_MASTER_PRINT2
prompt =======================================
prompt
create sequence HOUSE.SEQ_PRH_MASTER_PRINT2
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_MASTER_RATE
prompt =====================================
prompt
create sequence HOUSE.SEQ_PRH_MASTER_RATE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_MASTER_RENEW
prompt ======================================
prompt
create sequence HOUSE.SEQ_PRH_MASTER_RENEW
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_PAYMENT
prompt =================================
prompt
create sequence HOUSE.SEQ_PRH_PAYMENT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_PAYMENT2
prompt ==================================
prompt
create sequence HOUSE.SEQ_PRH_PAYMENT2
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_PRH_ROOMNO
prompt ====================================
prompt
create sequence HOUSE.SEQ_PRH_PRH_ROOMNO
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_RENTAL
prompt ================================
prompt
create sequence HOUSE.SEQ_PRH_RENTAL
minvalue 1
maxvalue 9999999999999999999999999999
start with 29490
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_RENTAL2
prompt =================================
prompt
create sequence HOUSE.SEQ_PRH_RENTAL2
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_RENTAL_DEL
prompt ====================================
prompt
create sequence HOUSE.SEQ_PRH_RENTAL_DEL
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_RENTAL_EXT
prompt ====================================
prompt
create sequence HOUSE.SEQ_PRH_RENTAL_EXT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_RENTAL_MONTH
prompt ======================================
prompt
create sequence HOUSE.SEQ_PRH_RENTAL_MONTH
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_RMDEV
prompt ===============================
prompt
create sequence HOUSE.SEQ_PRH_RMDEV
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_RMOOO
prompt ===============================
prompt
create sequence HOUSE.SEQ_PRH_RMOOO
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_RMRATE_ADJUST
prompt =======================================
prompt
create sequence HOUSE.SEQ_PRH_RMRATE_ADJUST
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_ROOMNO_TMP
prompt ====================================
prompt
create sequence HOUSE.SEQ_PRH_ROOMNO_TMP
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_SUBSIDY
prompt =================================
prompt
create sequence HOUSE.SEQ_PRH_SUBSIDY
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRH_SUBSIDY_DTL
prompt =====================================
prompt
create sequence HOUSE.SEQ_PRH_SUBSIDY_DTL
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PROFILE
prompt =============================
prompt
create sequence HOUSE.SEQ_PROFILE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PR_HOUSE
prompt ==============================
prompt
create sequence HOUSE.SEQ_PR_HOUSE
minvalue 1
maxvalue 9999999999999999999999999999
start with 14304
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_RENT_MENU
prompt ===============================
prompt
create sequence HOUSE.SEQ_RENT_MENU
minvalue 1
maxvalue 9999999999999999999999999999
start with 101
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_RENT_PAY_WAY
prompt ==================================
prompt
create sequence HOUSE.SEQ_RENT_PAY_WAY
minvalue 1
maxvalue 9999999999999999999999999999
start with 201
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_REPAIRE_REASON
prompt ====================================
prompt
create sequence HOUSE.SEQ_REPAIRE_REASON
minvalue 1
maxvalue 9999999999999999999999999999
start with 61
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ROLE
prompt ==========================
prompt
create sequence HOUSE.SEQ_ROLE
minvalue 1
maxvalue 9999999999999999999999999999
start with 161
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ROOM_FEATURE
prompt ==================================
prompt
create sequence HOUSE.SEQ_ROOM_FEATURE
minvalue 1
maxvalue 9999999999999999999999999999
start with 41
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ROOM_ID
prompt =============================
prompt
create sequence HOUSE.SEQ_ROOM_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ROOM_NUM
prompt ==============================
prompt
create sequence HOUSE.SEQ_ROOM_NUM
minvalue 1
maxvalue 9999999999999999999999999999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ROOM_TYPE
prompt ===============================
prompt
create sequence HOUSE.SEQ_ROOM_TYPE
minvalue 1
maxvalue 9999999999999999999999999999
start with 641
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS
prompt =========================
prompt
create sequence HOUSE.SEQ_SMS
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS_CODE
prompt ==============================
prompt
create sequence HOUSE.SEQ_SMS_CODE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS_HIS
prompt =============================
prompt
create sequence HOUSE.SEQ_SMS_HIS
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS_KEYWORDS
prompt ==================================
prompt
create sequence HOUSE.SEQ_SMS_KEYWORDS
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS_KFOLIO
prompt ================================
prompt
create sequence HOUSE.SEQ_SMS_KFOLIO
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS_KMOBILE
prompt =================================
prompt
create sequence HOUSE.SEQ_SMS_KMOBILE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS_LIBRARY
prompt =================================
prompt
create sequence HOUSE.SEQ_SMS_LIBRARY
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SUBSIDY
prompt =============================
prompt
create sequence HOUSE.SEQ_SUBSIDY
minvalue 1
maxvalue 9999999999999999999999999999
start with 161
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SUBSIDY_PERCENT
prompt =====================================
prompt
create sequence HOUSE.SEQ_SUBSIDY_PERCENT
minvalue 1
maxvalue 9999999999999999999999999999
start with 61
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SUBSIDY_TYPE
prompt ==================================
prompt
create sequence HOUSE.SEQ_SUBSIDY_TYPE
minvalue 1
maxvalue 9999999999999999999999999999
start with 102
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SUBSIDY_WITH_TYPE
prompt =======================================
prompt
create sequence HOUSE.SEQ_SUBSIDY_WITH_TYPE
minvalue 1
maxvalue 9999999999999999999999999999
start with 765
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_TABLE_LOG
prompt ===============================
prompt
create sequence HOUSE.SEQ_TABLE_LOG
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_USERS
prompt ===========================
prompt
create sequence HOUSE.SEQ_USERS
minvalue 1
maxvalue 9999999999999999999999999999
start with 218
increment by 1
cache 20;

