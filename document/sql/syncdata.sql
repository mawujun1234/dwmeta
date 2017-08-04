prompt PL/SQL Developer import file
prompt Created on 2017年8月4日 by mawujun
set feedback off
set define off
prompt Dropping DW_CLASSIFY...
drop table DW_CLASSIFY cascade constraints;
prompt Dropping DW_COLCLASSIFY...
drop table DW_COLCLASSIFY cascade constraints;
prompt Dropping DW_COLDEFINE...
drop table DW_COLDEFINE cascade constraints;
prompt Dropping DW_COLUMNMETA...
drop table DW_COLUMNMETA cascade constraints;
prompt Dropping DW_COL_CLASSIFY...
drop table DW_COL_CLASSIFY cascade constraints;
prompt Dropping DW_DB...
drop table DW_DB cascade constraints;
prompt Dropping DW_DWLAYER...
drop table DW_DWLAYER cascade constraints;
prompt Dropping DW_HISTORY...
drop table DW_HISTORY cascade constraints;
prompt Dropping DW_HISTORY_COLETA...
drop table DW_HISTORY_COLETA cascade constraints;
prompt Dropping DW_HISTORY_TABMETA...
drop table DW_HISTORY_TABMETA cascade constraints;
prompt Dropping DW_KPI...
drop table DW_KPI cascade constraints;
prompt Dropping DW_TABLEMETA...
drop table DW_TABLEMETA cascade constraints;
prompt Dropping DW_TABLEMETAHIS...
drop table DW_TABLEMETAHIS cascade constraints;
prompt Dropping HR_ORG...
drop table HR_ORG cascade constraints;
prompt Dropping HR_POSITION...
drop table HR_POSITION cascade constraints;
prompt Dropping T_CONSTANT...
drop table T_CONSTANT cascade constraints;
prompt Dropping T_CONSTANTITEM...
drop table T_CONSTANTITEM cascade constraints;
prompt Dropping T_MENU...
drop table T_MENU cascade constraints;
prompt Dropping T_ROLE...
drop table T_ROLE cascade constraints;
prompt Dropping T_ROLE_MENU...
drop table T_ROLE_MENU cascade constraints;
prompt Dropping T_USER...
drop table T_USER cascade constraints;
prompt Dropping T_ROLE_USER...
drop table T_ROLE_USER cascade constraints;
prompt Creating DW_CLASSIFY...
create table DW_CLASSIFY
(
  id         VARCHAR2(36 CHAR) not null,
  dwlayer_id VARCHAR2(36 CHAR),
  name       VARCHAR2(30 CHAR) not null,
  parent_id  VARCHAR2(36 CHAR),
  remark     VARCHAR2(150 CHAR)
)
;
alter table DW_CLASSIFY
  add primary key (ID);

prompt Creating DW_COLCLASSIFY...
create table DW_COLCLASSIFY
(
  id      VARCHAR2(36 CHAR) not null,
  db_id   VARCHAR2(36 CHAR) not null,
  deleted NUMBER(1),
  name    VARCHAR2(30 CHAR) not null,
  remark  VARCHAR2(150 CHAR)
)
;
alter table DW_COLCLASSIFY
  add primary key (ID);

prompt Creating DW_COLDEFINE...
create table DW_COLDEFINE
(
  id             VARCHAR2(36 CHAR) not null,
  colclassify_id VARCHAR2(36 CHAR),
  colname        VARCHAR2(30 CHAR) not null,
  coltype        VARCHAR2(30 CHAR) not null,
  definition     VARCHAR2(150 CHAR),
  name           VARCHAR2(30 CHAR) not null,
  remark         VARCHAR2(150 CHAR),
  status         NUMBER(1)
)
;
alter table DW_COLDEFINE
  add primary key (ID);

prompt Creating DW_COLUMNMETA...
create table DW_COLUMNMETA
(
  id           VARCHAR2(36 CHAR) not null,
  collen       VARCHAR2(30 CHAR),
  colname      VARCHAR2(30 CHAR) not null,
  coltype      VARCHAR2(30 CHAR) not null,
  comments     VARCHAR2(100 CHAR),
  defaultvalue VARCHAR2(50 CHAR),
  ispk         NUMBER(1),
  name         VARCHAR2(30 CHAR),
  nullable     NUMBER(1),
  sorted       NUMBER(10),
  tablemeta_id VARCHAR2(36 CHAR),
  reasons      VARCHAR2(200 CHAR)
)
;
alter table DW_COLUMNMETA
  add primary key (ID);

prompt Creating DW_COL_CLASSIFY...
create table DW_COL_CLASSIFY
(
  id      VARCHAR2(36 CHAR) not null,
  db_id   VARCHAR2(36 CHAR) not null,
  name    VARCHAR2(30 CHAR) not null,
  remark  VARCHAR2(150 CHAR),
  deleted NUMBER(1)
)
;
alter table DW_COL_CLASSIFY
  add primary key (ID);

prompt Creating DW_DB...
create table DW_DB
(
  id     VARCHAR2(36 CHAR) not null,
  name   VARCHAR2(50 CHAR),
  remark VARCHAR2(150 CHAR),
  dbtype VARCHAR2(50 CHAR),
  ddd    VARCHAR2(21)
)
;
alter table DW_DB
  add primary key (ID);

prompt Creating DW_DWLAYER...
create table DW_DWLAYER
(
  id            VARCHAR2(36 CHAR) not null,
  jdbc_driver   VARCHAR2(50 CHAR) not null,
  jdbc_password VARCHAR2(50 CHAR) not null,
  jdbc_url      VARCHAR2(50 CHAR) not null,
  jdbc_username VARCHAR2(50 CHAR) not null,
  name          VARCHAR2(30 CHAR) not null,
  remark        VARCHAR2(150 CHAR),
  db_id         VARCHAR2(36 CHAR)
)
;
alter table DW_DWLAYER
  add primary key (ID);

prompt Creating DW_HISTORY...
create table DW_HISTORY
(
  id           NUMBER(19) not null,
  intiactive   NUMBER(1),
  operatetime  TIMESTAMP(6),
  operatetype  VARCHAR2(36 CHAR) not null,
  operater     VARCHAR2(36 CHAR) not null,
  tablemeta_id VARCHAR2(36 CHAR) not null
)
;
alter table DW_HISTORY
  add primary key (ID);

prompt Creating DW_HISTORY_COLETA...
create table DW_HISTORY_COLETA
(
  id             VARCHAR2(36 CHAR) not null,
  collen         VARCHAR2(30 CHAR),
  colname        VARCHAR2(30 CHAR) not null,
  coltype        VARCHAR2(30 CHAR) not null,
  comments       VARCHAR2(100 CHAR),
  defaultvalue   VARCHAR2(50 CHAR),
  his_content    VARCHAR2(36 CHAR) not null,
  history_id     NUMBER(19) not null,
  ispk           NUMBER(1),
  name           VARCHAR2(30 CHAR),
  nullable       NUMBER(1),
  reasons        VARCHAR2(200 CHAR),
  sorted         NUMBER(10),
  tablemeta_id   VARCHAR2(36 CHAR),
  his_createdate TIMESTAMP(6)
)
;
alter table DW_HISTORY_COLETA
  add primary key (ID);

prompt Creating DW_HISTORY_TABMETA...
create table DW_HISTORY_TABMETA
(
  id             VARCHAR2(36 CHAR) not null,
  classify_id    VARCHAR2(36 CHAR) not null,
  entitytype     VARCHAR2(20 CHAR),
  his_content    VARCHAR2(36 CHAR) not null,
  history_id     NUMBER(19) not null,
  name           VARCHAR2(30 CHAR),
  remark         VARCHAR2(150 CHAR),
  tablename      VARCHAR2(30 CHAR) not null,
  his_createdate TIMESTAMP(6)
)
;
alter table DW_HISTORY_TABMETA
  add primary key (ID);

prompt Creating DW_KPI...
create table DW_KPI
(
  id          VARCHAR2(36 CHAR) not null,
  code        VARCHAR2(50 CHAR) not null,
  definition  VARCHAR2(150 CHAR),
  kpi_type_id VARCHAR2(36 CHAR),
  name        VARCHAR2(50 CHAR) not null,
  remark      VARCHAR2(150 CHAR),
  status      NUMBER(1)
)
;
alter table DW_KPI
  add primary key (ID);

prompt Creating DW_TABLEMETA...
create table DW_TABLEMETA
(
  id          VARCHAR2(36 CHAR) not null,
  classify_id VARCHAR2(36 CHAR),
  name        VARCHAR2(30 CHAR),
  remark      VARCHAR2(150 CHAR),
  tablename   VARCHAR2(30 CHAR) not null,
  entitytype  VARCHAR2(20 CHAR)
)
;
alter table DW_TABLEMETA
  add primary key (ID);

prompt Creating DW_TABLEMETAHIS...
create table DW_TABLEMETAHIS
(
  id           VARCHAR2(36 CHAR) not null,
  collen       VARCHAR2(30 CHAR),
  colname      VARCHAR2(30 CHAR) not null,
  coltype      VARCHAR2(30 CHAR) not null,
  comments     VARCHAR2(100 CHAR),
  content      VARCHAR2(36 CHAR) not null,
  defaultvalue VARCHAR2(50 CHAR),
  history_id   NUMBER(19) not null,
  ispk         NUMBER(1),
  name         VARCHAR2(30 CHAR),
  nullable     NUMBER(1),
  reasons      VARCHAR2(200 CHAR),
  sorted       NUMBER(10),
  tablemeta_id VARCHAR2(36 CHAR),
  classify_id  VARCHAR2(36 CHAR) not null,
  remark       VARCHAR2(150 CHAR),
  tablename    VARCHAR2(30 CHAR) not null,
  entitytype   VARCHAR2(20 CHAR)
)
;
alter table DW_TABLEMETAHIS
  add primary key (ID);

prompt Creating HR_ORG...
create table HR_ORG
(
  id           VARCHAR2(36 CHAR) not null,
  address      VARCHAR2(80 CHAR),
  code         VARCHAR2(30 CHAR),
  createdate   TIMESTAMP(6),
  email        VARCHAR2(50 CHAR),
  enddate      TIMESTAMP(6),
  fax          VARCHAR2(30 CHAR),
  introduction VARCHAR2(300 CHAR),
  isdel        NUMBER(1),
  isroot       NUMBER(1),
  layer        NUMBER(10),
  name         VARCHAR2(30 CHAR),
  operatetime  TIMESTAMP(6),
  operator_id  VARCHAR2(36 CHAR),
  orgtype_id   VARCHAR2(36 CHAR),
  parent_id    VARCHAR2(36 CHAR),
  phonenumber  VARCHAR2(30 CHAR),
  postalcode   VARCHAR2(10 CHAR),
  sort         NUMBER(10),
  status       NUMBER(1),
  web          VARCHAR2(120 CHAR)
)
;
alter table HR_ORG
  add primary key (ID);

prompt Creating HR_POSITION...
create table HR_POSITION
(
  id              VARCHAR2(36 CHAR) not null,
  name            VARCHAR2(30 CHAR) not null,
  org_id          VARCHAR2(36 CHAR) not null,
  positiontype_id VARCHAR2(36 CHAR),
  remark          VARCHAR2(300 CHAR)
)
;
alter table HR_POSITION
  add primary key (ID);

prompt Creating T_CONSTANT...
create table T_CONSTANT
(
  id   VARCHAR2(36 CHAR) not null,
  name VARCHAR2(30 CHAR),
  sort NUMBER(10)
)
;
alter table T_CONSTANT
  add primary key (ID);

prompt Creating T_CONSTANTITEM...
create table T_CONSTANTITEM
(
  id          VARCHAR2(36 CHAR) not null,
  constant_id VARCHAR2(36 CHAR) not null,
  name        VARCHAR2(30 CHAR),
  sort        NUMBER(10),
  status      NUMBER(1)
)
;
alter table T_CONSTANTITEM
  add primary key (ID);

prompt Creating T_MENU...
create table T_MENU
(
  id        VARCHAR2(36 CHAR) not null,
  code      VARCHAR2(50 CHAR),
  leaf      NUMBER(1),
  menutype  VARCHAR2(15 CHAR) not null,
  name      VARCHAR2(30 CHAR) not null,
  parent_id VARCHAR2(36 CHAR),
  remark    VARCHAR2(150 CHAR),
  sort      NUMBER(10),
  url       VARCHAR2(80 CHAR)
)
;
alter table T_MENU
  add primary key (ID);
alter table T_MENU
  add constraint UK_QQ80N1A6R2G45J9LK43CHS8Y4 unique (CODE);

prompt Creating T_ROLE...
create table T_ROLE
(
  id        VARCHAR2(36 CHAR) not null,
  name      VARCHAR2(30 CHAR) not null,
  parent_id VARCHAR2(36 CHAR),
  remark    VARCHAR2(150 CHAR),
  roletype  VARCHAR2(15 CHAR) not null
)
;
alter table T_ROLE
  add primary key (ID);

prompt Creating T_ROLE_MENU...
create table T_ROLE_MENU
(
  role_id VARCHAR2(36 CHAR) not null,
  menu_id VARCHAR2(36 CHAR) not null
)
;
alter table T_ROLE_MENU
  add primary key (ROLE_ID, MENU_ID);
alter table T_ROLE_MENU
  add constraint FKHAYG4IB6V7H1WYEYXHQ6XLDDQ foreign key (MENU_ID)
  references T_MENU (ID);
alter table T_ROLE_MENU
  add constraint FKSONB0RBT2U99HBRQQVV3R0WSE foreign key (ROLE_ID)
  references T_ROLE (ID);

prompt Creating T_USER...
create table T_USER
(
  id            VARCHAR2(36 CHAR) not null,
  candel        NUMBER(1),
  email         VARCHAR2(30 CHAR),
  lastlogintime TIMESTAMP(6),
  loginname     VARCHAR2(30 CHAR) not null,
  mobile        VARCHAR2(30 CHAR),
  name          VARCHAR2(50 CHAR) not null,
  phone         VARCHAR2(30 CHAR),
  pwd           VARCHAR2(30 CHAR) not null,
  remark        VARCHAR2(150 CHAR),
  state         NUMBER(1)
)
;
alter table T_USER
  add primary key (ID);
alter table T_USER
  add constraint UK_BMNGCIQBCRW3YQDCFDWH8YSU7 unique (LOGINNAME);

prompt Creating T_ROLE_USER...
create table T_ROLE_USER
(
  user_id VARCHAR2(36 CHAR) not null,
  role_id VARCHAR2(36 CHAR) not null
)
;
alter table T_ROLE_USER
  add primary key (USER_ID, ROLE_ID);
alter table T_ROLE_USER
  add constraint FK22EEVO9IWSJ83BG5T8QTPVC3B foreign key (ROLE_ID)
  references T_ROLE (ID);
alter table T_ROLE_USER
  add constraint FK7VPGDV8N0P61TSPKGG833AFHI foreign key (USER_ID)
  references T_USER (ID);

prompt Disabling triggers for DW_CLASSIFY...
alter table DW_CLASSIFY disable all triggers;
prompt Disabling triggers for DW_COLCLASSIFY...
alter table DW_COLCLASSIFY disable all triggers;
prompt Disabling triggers for DW_COLDEFINE...
alter table DW_COLDEFINE disable all triggers;
prompt Disabling triggers for DW_COLUMNMETA...
alter table DW_COLUMNMETA disable all triggers;
prompt Disabling triggers for DW_COL_CLASSIFY...
alter table DW_COL_CLASSIFY disable all triggers;
prompt Disabling triggers for DW_DB...
alter table DW_DB disable all triggers;
prompt Disabling triggers for DW_DWLAYER...
alter table DW_DWLAYER disable all triggers;
prompt Disabling triggers for DW_HISTORY...
alter table DW_HISTORY disable all triggers;
prompt Disabling triggers for DW_HISTORY_COLETA...
alter table DW_HISTORY_COLETA disable all triggers;
prompt Disabling triggers for DW_HISTORY_TABMETA...
alter table DW_HISTORY_TABMETA disable all triggers;
prompt Disabling triggers for DW_KPI...
alter table DW_KPI disable all triggers;
prompt Disabling triggers for DW_TABLEMETA...
alter table DW_TABLEMETA disable all triggers;
prompt Disabling triggers for DW_TABLEMETAHIS...
alter table DW_TABLEMETAHIS disable all triggers;
prompt Disabling triggers for HR_ORG...
alter table HR_ORG disable all triggers;
prompt Disabling triggers for HR_POSITION...
alter table HR_POSITION disable all triggers;
prompt Disabling triggers for T_CONSTANT...
alter table T_CONSTANT disable all triggers;
prompt Disabling triggers for T_CONSTANTITEM...
alter table T_CONSTANTITEM disable all triggers;
prompt Disabling triggers for T_MENU...
alter table T_MENU disable all triggers;
prompt Disabling triggers for T_ROLE...
alter table T_ROLE disable all triggers;
prompt Disabling triggers for T_ROLE_MENU...
alter table T_ROLE_MENU disable all triggers;
prompt Disabling triggers for T_USER...
alter table T_USER disable all triggers;
prompt Disabling triggers for T_ROLE_USER...
alter table T_ROLE_USER disable all triggers;
prompt Disabling foreign key constraints for T_ROLE_MENU...
alter table T_ROLE_MENU disable constraint FKHAYG4IB6V7H1WYEYXHQ6XLDDQ;
alter table T_ROLE_MENU disable constraint FKSONB0RBT2U99HBRQQVV3R0WSE;
prompt Disabling foreign key constraints for T_ROLE_USER...
alter table T_ROLE_USER disable constraint FK22EEVO9IWSJ83BG5T8QTPVC3B;
alter table T_ROLE_USER disable constraint FK7VPGDV8N0P61TSPKGG833AFHI;
prompt Loading DW_CLASSIFY...
insert into DW_CLASSIFY (id, dwlayer_id, name, parent_id, remark)
values ('aaa', 'ac6c939f-5c18-4dd8-954d-a01c14c5b5c6', '默认', null, null);
insert into DW_CLASSIFY (id, dwlayer_id, name, parent_id, remark)
values ('ec92e5b8-1109-47ab-8f8d-063875a2f3ba', '79f04f9e-6a26-45eb-87b3-3cf945f92885', '默认分类', null, null);
insert into DW_CLASSIFY (id, dwlayer_id, name, parent_id, remark)
values ('1ff79dc7-c49a-43f1-8edc-e4664289bd1c', '0d0a7e13-e718-44a6-97c2-53219968a1fe', '默认分类', null, null);
insert into DW_CLASSIFY (id, dwlayer_id, name, parent_id, remark)
values ('5789f690-7b14-475e-b546-c43928e5648c', 'ac6c939f-5c18-4dd8-954d-a01c14c5b5c6', '112', null, null);
commit;
prompt 4 records loaded
prompt Loading DW_COLCLASSIFY...
insert into DW_COLCLASSIFY (id, db_id, deleted, name, remark)
values ('9b4b8775-534c-4b82-a84f-67df7255553f', '0170cf9e-1de7-43e6-9046-d2d57da52a11', 0, 'KPI指标维护', null);
commit;
prompt 1 records loaded
prompt Loading DW_COLDEFINE...
insert into DW_COLDEFINE (id, colclassify_id, colname, coltype, definition, name, remark, status)
values ('5445a119-94de-4aac-a313-c5d81496af29', '9b4b8775-534c-4b82-a84f-67df7255553f', '111', 'CHAR(50)', 'dsfsdfsdfdddd' || chr(10) || 'dfdfdfdfdf', '5555', 'dfdf', 0);
insert into DW_COLDEFINE (id, colclassify_id, colname, coltype, definition, name, remark, status)
values ('ec3d5132-3788-4e7b-9843-d33f5bc3ee16', '9b4b8775-534c-4b82-a84f-67df7255553f', 'sdfsd', 'NVARCHAR2(122)', 'sdsdf' || chr(10) || 'sdf' || chr(10) || 'sdfsdf' || chr(10) || 'sdfsdf', 'sdfsdf', null, 1);
commit;
prompt 2 records loaded
prompt Loading DW_COLUMNMETA...
insert into DW_COLUMNMETA (id, collen, colname, coltype, comments, defaultvalue, ispk, name, nullable, sorted, tablemeta_id, reasons)
values ('1328ad79-1ba4-4297-92e7-69c7947be337', null, '1', '11', null, null, 0, 'fff', 0, 1, 'bd89dbbf-463d-44cf-9265-d3fafa4b0338', null);
insert into DW_COLUMNMETA (id, collen, colname, coltype, comments, defaultvalue, ispk, name, nullable, sorted, tablemeta_id, reasons)
values ('1d93bd71-d112-4061-8c8e-69dda09fbe56', null, '2', '2', null, null, 0, 'sdf', 0, 1, 'e54f6ec7-29df-42db-8e3f-4e7ea991146d', null);
insert into DW_COLUMNMETA (id, collen, colname, coltype, comments, defaultvalue, ispk, name, nullable, sorted, tablemeta_id, reasons)
values ('5296cb1e-023c-428a-bab8-53a706feae4f', null, '222', 'CHAR(10)', null, '22', 0, '22', 0, 6, 'c62f8655-ead3-4115-a46a-17db73aad013', null);
insert into DW_COLUMNMETA (id, collen, colname, coltype, comments, defaultvalue, ispk, name, nullable, sorted, tablemeta_id, reasons)
values ('82c8f082-7469-4571-ad04-3eadd6696bfe', null, 'DDD', 'DDD', null, null, 0, null, 0, 1, 'c62f8655-ead3-4115-a46a-17db73aad013', null);
insert into DW_COLUMNMETA (id, collen, colname, coltype, comments, defaultvalue, ispk, name, nullable, sorted, tablemeta_id, reasons)
values ('4fe40860-2320-4650-a053-88758b984971', null, '11', '111', null, null, 0, '111', 0, 2, 'c62f8655-ead3-4115-a46a-17db73aad013', null);
insert into DW_COLUMNMETA (id, collen, colname, coltype, comments, defaultvalue, ispk, name, nullable, sorted, tablemeta_id, reasons)
values ('eb3ea1dd-7f11-43fd-b1d1-efbe987f5708', null, '22', '333', null, null, 0, null, 0, 1, 'c1bb23ac-8cec-417a-8792-0731ef7e1580', null);
insert into DW_COLUMNMETA (id, collen, colname, coltype, comments, defaultvalue, ispk, name, nullable, sorted, tablemeta_id, reasons)
values ('7fc4c8e8-0bab-4696-8986-55137aa23bf9', null, 'DDD', 'DD', null, null, 0, 'DDD', 0, 3, 'c62f8655-ead3-4115-a46a-17db73aad013', null);
insert into DW_COLUMNMETA (id, collen, colname, coltype, comments, defaultvalue, ispk, name, nullable, sorted, tablemeta_id, reasons)
values ('4874de44-0182-40a6-ba58-fa4c0f017a52', null, '212', '1212', null, null, 0, null, 0, 4, 'c62f8655-ead3-4115-a46a-17db73aad013', null);
insert into DW_COLUMNMETA (id, collen, colname, coltype, comments, defaultvalue, ispk, name, nullable, sorted, tablemeta_id, reasons)
values ('9d1d8d73-5037-4e7d-8e97-70359e73a8ea', null, 'WERWER', 'WERWER', null, null, 0, null, 0, 5, 'c62f8655-ead3-4115-a46a-17db73aad013', null);
commit;
prompt 9 records loaded
prompt Loading DW_COL_CLASSIFY...
insert into DW_COL_CLASSIFY (id, db_id, name, remark, deleted)
values ('199f8c17-95fe-42bb-bfc7-03675b3679be', '0170cf9e-1de7-43e6-9046-d2d57da52a11', '222', null, null);
commit;
prompt 1 records loaded
prompt Loading DW_DB...
insert into DW_DB (id, name, remark, dbtype, ddd)
values ('0170cf9e-1de7-43e6-9046-d2d57da52a11', '数据仓库', null, 'oracle', null);
insert into DW_DB (id, name, remark, dbtype, ddd)
values ('6b3f49f6-793c-4218-a4bc-71c5732031e2', 'hr数据库', null, 'sqlserver', null);
commit;
prompt 2 records loaded
prompt Loading DW_DWLAYER...
insert into DW_DWLAYER (id, jdbc_driver, jdbc_password, jdbc_url, jdbc_username, name, remark, db_id)
values ('79f04f9e-6a26-45eb-87b3-3cf945f92885', '111', '111', '111', '11', '111', null, null);
insert into DW_DWLAYER (id, jdbc_driver, jdbc_password, jdbc_url, jdbc_username, name, remark, db_id)
values ('0d0a7e13-e718-44a6-97c2-53219968a1fe', '111', '111', '111', '111', '111', null, '6b3f49f6-793c-4218-a4bc-71c5732031e2');
insert into DW_DWLAYER (id, jdbc_driver, jdbc_password, jdbc_url, jdbc_username, name, remark, db_id)
values ('ac6c939f-5c18-4dd8-954d-a01c14c5b5c6', 'oracle.jdbc.driver.OracleDriver', 'yzf1981', 'jdbc:oracle:thin:@192.16.188.130:1521:BI', 'ods', 'ODS', null, '0170cf9e-1de7-43e6-9046-d2d57da52a11');
commit;
prompt 3 records loaded
prompt Loading DW_HISTORY...
prompt Table is empty
prompt Loading DW_HISTORY_COLETA...
prompt Table is empty
prompt Loading DW_HISTORY_TABMETA...
prompt Table is empty
prompt Loading DW_KPI...
prompt Table is empty
prompt Loading DW_TABLEMETA...
insert into DW_TABLEMETA (id, classify_id, name, remark, tablename, entitytype)
values ('c62f8655-ead3-4115-a46a-17db73aad013', 'aaa', null, null, '111', 'view');
insert into DW_TABLEMETA (id, classify_id, name, remark, tablename, entitytype)
values ('3c5718cc-97e2-4501-88a1-5d2eedf6e7a5', 'aaa', null, null, 'sdfsdfsdf', 'table');
insert into DW_TABLEMETA (id, classify_id, name, remark, tablename, entitytype)
values ('f5ac3af4-4677-45d8-b891-8e76197006bf', '1ff79dc7-c49a-43f1-8edc-e4664289bd1c', null, null, '111', 'table');
insert into DW_TABLEMETA (id, classify_id, name, remark, tablename, entitytype)
values ('c1bb23ac-8cec-417a-8792-0731ef7e1580', 'aaa', null, null, '112', 'table');
insert into DW_TABLEMETA (id, classify_id, name, remark, tablename, entitytype)
values ('e54f6ec7-29df-42db-8e3f-4e7ea991146d', 'aaa', '222', null, '1111', 'table');
insert into DW_TABLEMETA (id, classify_id, name, remark, tablename, entitytype)
values ('bd89dbbf-463d-44cf-9265-d3fafa4b0338', 'aaa', '你好啊', null, '22222', 'table');
commit;
prompt 6 records loaded
prompt Loading DW_TABLEMETAHIS...
prompt Table is empty
prompt Loading HR_ORG...
insert into HR_ORG (id, address, code, createdate, email, enddate, fax, introduction, isdel, isroot, layer, name, operatetime, operator_id, orgtype_id, parent_id, phonenumber, postalcode, sort, status, web)
values ('root', null, 'root', null, null, null, null, null, 0, 1, 0, '根节点', null, null, null, null, null, null, 0, 1, null);
commit;
prompt 1 records loaded
prompt Loading HR_POSITION...
prompt Table is empty
prompt Loading T_CONSTANT...
insert into T_CONSTANT (id, name, sort)
values ('kpi_type', '指标类型', 0);
commit;
prompt 1 records loaded
prompt Loading T_CONSTANTITEM...
prompt Table is empty
prompt Loading T_MENU...
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('c8f4411b-6f80-4bbd-ba84-cc711af80a72', null, 1, 'menu', '常用列维护', null, null, 2, '/kpi/ColDefineApp.jsp');
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('ca51fe5d-6e24-4388-8249-f8f1ac6f4feb', null, 1, 'menu', '常数维护', '1eb1d9ea-075c-43c6-baa3-d36004f4d4b7', null, 0, '/constant/ConstantApp.jsp');
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('1eb1d9ea-075c-43c6-baa3-d36004f4d4b7', null, 0, 'menu', '基础维护', null, null, 0, null);
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('539ea37c-a286-4ae2-8a0b-cb4dab321515', null, 1, 'menu', '菜单管理', '1eb1d9ea-075c-43c6-baa3-d36004f4d4b7', null, 0, '/permission/MenuApp.jsp');
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('e35d2b7f-58c2-4e61-a50d-bc6112779b4c', null, 1, 'menu', '角色管理', '1eb1d9ea-075c-43c6-baa3-d36004f4d4b7', null, 0, '/permission/RoleApp.jsp');
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('a124a5f1-70a3-4dba-a014-68385edd922b', null, 1, 'menu', '组织岗位管理', '1eb1d9ea-075c-43c6-baa3-d36004f4d4b7', null, 0, '/org/OrgApp.jsp');
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('a7439ed8-ad09-452f-9860-27157c9027d9', null, 1, 'menu', '用户管理', '1eb1d9ea-075c-43c6-baa3-d36004f4d4b7', null, 0, '/permission/UserApp.jsp');
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('621ddc60-547e-4fc2-8752-d5f6a4d620ed', null, 1, 'menu', '11', null, null, null, '/dwmeta/DwmetaApp.jsp?db_id=8669dd1a-eaa4-40e7-af33-1986965c33e9');
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('0ec73c74-a105-4cd6-840c-f2021acdd6aa', null, 1, 'menu', '元数据综合查询', null, null, 1, null);
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('972d70ac-fe0e-4963-8ad3-67b01bca28ad', null, 1, 'menu', 'DW元数据管理', null, null, 2, '/dwmeta/DwmetaApp.jsp?db_id=0170cf9e-1de7-43e6-9046-d2d57da52a11');
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('8bd386e1-781c-44f9-af88-a43623c13c0b', null, 1, 'menu', 'KPI指标库管理', null, null, 0, '/kpi/KpiApp.jsp');
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('878fb569-0ef6-400a-afde-655f4aca00df', null, 1, 'menu', '数据库配置', null, null, 0, '/dwmeta/DBApp.jsp');
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('c4e470ce-08b5-4383-be56-3be960577a25', null, 1, 'menu', 'hr数据库', null, null, null, '/dwmeta/DwmetaApp.jsp?db_id=6b3f49f6-793c-4218-a4bc-71c5732031e2');
commit;
prompt 13 records loaded
prompt Loading T_ROLE...
insert into T_ROLE (id, name, parent_id, remark, roletype)
values ('admin', 'admin', null, null, 'role');
insert into T_ROLE (id, name, parent_id, remark, roletype)
values ('c2977910-8a4f-49a7-a04e-01d7dc3198c2', '作业单位', null, null, 'role');
commit;
prompt 2 records loaded
prompt Loading T_ROLE_MENU...
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', '0ec73c74-a105-4cd6-840c-f2021acdd6aa');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', '1eb1d9ea-075c-43c6-baa3-d36004f4d4b7');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', '539ea37c-a286-4ae2-8a0b-cb4dab321515');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', '878fb569-0ef6-400a-afde-655f4aca00df');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', '8bd386e1-781c-44f9-af88-a43623c13c0b');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', '972d70ac-fe0e-4963-8ad3-67b01bca28ad');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', 'a124a5f1-70a3-4dba-a014-68385edd922b');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', 'a7439ed8-ad09-452f-9860-27157c9027d9');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', 'c4e470ce-08b5-4383-be56-3be960577a25');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', 'c8f4411b-6f80-4bbd-ba84-cc711af80a72');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', 'ca51fe5d-6e24-4388-8249-f8f1ac6f4feb');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', 'e35d2b7f-58c2-4e61-a50d-bc6112779b4c');
commit;
prompt 12 records loaded
prompt Loading T_USER...
insert into T_USER (id, candel, email, lastlogintime, loginname, mobile, name, phone, pwd, remark, state)
values ('admin', 0, null, to_timestamp('04-08-2017 13:46:39.853000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', null, 'admin', null, 'admin', null, 1);
commit;
prompt 1 records loaded
prompt Loading T_ROLE_USER...
insert into T_ROLE_USER (user_id, role_id)
values ('admin', 'admin');
commit;
prompt 1 records loaded
prompt Enabling foreign key constraints for T_ROLE_MENU...
alter table T_ROLE_MENU enable constraint FKHAYG4IB6V7H1WYEYXHQ6XLDDQ;
alter table T_ROLE_MENU enable constraint FKSONB0RBT2U99HBRQQVV3R0WSE;
prompt Enabling foreign key constraints for T_ROLE_USER...
alter table T_ROLE_USER enable constraint FK22EEVO9IWSJ83BG5T8QTPVC3B;
alter table T_ROLE_USER enable constraint FK7VPGDV8N0P61TSPKGG833AFHI;
prompt Enabling triggers for DW_CLASSIFY...
alter table DW_CLASSIFY enable all triggers;
prompt Enabling triggers for DW_COLCLASSIFY...
alter table DW_COLCLASSIFY enable all triggers;
prompt Enabling triggers for DW_COLDEFINE...
alter table DW_COLDEFINE enable all triggers;
prompt Enabling triggers for DW_COLUMNMETA...
alter table DW_COLUMNMETA enable all triggers;
prompt Enabling triggers for DW_COL_CLASSIFY...
alter table DW_COL_CLASSIFY enable all triggers;
prompt Enabling triggers for DW_DB...
alter table DW_DB enable all triggers;
prompt Enabling triggers for DW_DWLAYER...
alter table DW_DWLAYER enable all triggers;
prompt Enabling triggers for DW_HISTORY...
alter table DW_HISTORY enable all triggers;
prompt Enabling triggers for DW_HISTORY_COLETA...
alter table DW_HISTORY_COLETA enable all triggers;
prompt Enabling triggers for DW_HISTORY_TABMETA...
alter table DW_HISTORY_TABMETA enable all triggers;
prompt Enabling triggers for DW_KPI...
alter table DW_KPI enable all triggers;
prompt Enabling triggers for DW_TABLEMETA...
alter table DW_TABLEMETA enable all triggers;
prompt Enabling triggers for DW_TABLEMETAHIS...
alter table DW_TABLEMETAHIS enable all triggers;
prompt Enabling triggers for HR_ORG...
alter table HR_ORG enable all triggers;
prompt Enabling triggers for HR_POSITION...
alter table HR_POSITION enable all triggers;
prompt Enabling triggers for T_CONSTANT...
alter table T_CONSTANT enable all triggers;
prompt Enabling triggers for T_CONSTANTITEM...
alter table T_CONSTANTITEM enable all triggers;
prompt Enabling triggers for T_MENU...
alter table T_MENU enable all triggers;
prompt Enabling triggers for T_ROLE...
alter table T_ROLE enable all triggers;
prompt Enabling triggers for T_ROLE_MENU...
alter table T_ROLE_MENU enable all triggers;
prompt Enabling triggers for T_USER...
alter table T_USER enable all triggers;
prompt Enabling triggers for T_ROLE_USER...
alter table T_ROLE_USER enable all triggers;
set feedback on
set define on
prompt Done.
