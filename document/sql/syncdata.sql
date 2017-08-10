prompt PL/SQL Developer import file
prompt Created on 2017年8月10日 by mawujun
set feedback off
set define off
prompt Dropping DW_CD_COLCLASSIFY...
drop table DW_CD_COLCLASSIFY cascade constraints;
prompt Dropping DW_CD_COLDEFINE...
drop table DW_CD_COLDEFINE cascade constraints;
prompt Dropping DW_CLASSIFY...
drop table DW_CLASSIFY cascade constraints;
prompt Dropping DW_COLUMNMETA...
drop table DW_COLUMNMETA cascade constraints;
prompt Dropping DW_CONSTRAINTS...
drop table DW_CONSTRAINTS cascade constraints;
prompt Dropping DW_CONS_COLS...
drop table DW_CONS_COLS cascade constraints;
prompt Dropping DW_DB...
drop table DW_DB cascade constraints;
prompt Dropping DW_DWLAYER...
drop table DW_DWLAYER cascade constraints;
prompt Dropping DW_HIS_COLMETA...
drop table DW_HIS_COLMETA cascade constraints;
prompt Dropping DW_HIS_HISTORY...
drop table DW_HIS_HISTORY cascade constraints;
prompt Dropping DW_HIS_TABMETA...
drop table DW_HIS_TABMETA cascade constraints;
prompt Dropping DW_KPI...
drop table DW_KPI cascade constraints;
prompt Dropping DW_TABLEMETA...
drop table DW_TABLEMETA cascade constraints;
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
prompt Creating DW_CD_COLCLASSIFY...
create table DW_CD_COLCLASSIFY
(
  id      VARCHAR2(36 CHAR) not null,
  db_id   VARCHAR2(36 CHAR) not null,
  deleted NUMBER(1),
  name    VARCHAR2(30 CHAR) not null,
  remark  VARCHAR2(150 CHAR)
)
;
alter table DW_CD_COLCLASSIFY
  add primary key (ID);

prompt Creating DW_CD_COLDEFINE...
create table DW_CD_COLDEFINE
(
  id             VARCHAR2(36 CHAR) not null,
  colclassify_id VARCHAR2(36 CHAR),
  colname        VARCHAR2(30 CHAR) not null,
  coltype        VARCHAR2(30 CHAR) not null,
  definition     VARCHAR2(250 CHAR),
  name           VARCHAR2(30 CHAR) not null,
  remark         VARCHAR2(150 CHAR),
  status         NUMBER(1)
)
;
alter table DW_CD_COLDEFINE
  add primary key (ID);

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

prompt Creating DW_COLUMNMETA...
create table DW_COLUMNMETA
(
  id           VARCHAR2(36 CHAR) not null,
  colname      VARCHAR2(30 CHAR) not null,
  coltype      VARCHAR2(30 CHAR) not null,
  comments     VARCHAR2(100 CHAR),
  defaultvalue VARCHAR2(50 CHAR),
  name         VARCHAR2(30 CHAR),
  nullable     NUMBER(1),
  reasons      VARCHAR2(200 CHAR),
  sorted       NUMBER(10),
  tablemeta_id VARCHAR2(36 CHAR)
)
;
alter table DW_COLUMNMETA
  add primary key (ID);

prompt Creating DW_CONSTRAINTS...
create table DW_CONSTRAINTS
(
  id           VARCHAR2(36 CHAR) not null,
  createtime   TIMESTAMP(6),
  creater      VARCHAR2(36 CHAR),
  name         VARCHAR2(36 CHAR),
  tablemeta_id VARCHAR2(36 CHAR),
  type         VARCHAR2(255 CHAR)
)
;
alter table DW_CONSTRAINTS
  add primary key (ID);

prompt Creating DW_CONS_COLS...
create table DW_CONS_COLS
(
  id             VARCHAR2(36 CHAR) not null,
  col_id         VARCHAR2(36 CHAR),
  constraints_id VARCHAR2(36 CHAR),
  ref_col_id     VARCHAR2(36 CHAR),
  ref_table_id   VARCHAR2(36 CHAR)
)
;
alter table DW_CONS_COLS
  add primary key (ID);

prompt Creating DW_DB...
create table DW_DB
(
  id     VARCHAR2(36 CHAR) not null,
  dbtype VARCHAR2(50 CHAR),
  name   VARCHAR2(50 CHAR),
  remark VARCHAR2(150 CHAR)
)
;
alter table DW_DB
  add primary key (ID);

prompt Creating DW_DWLAYER...
create table DW_DWLAYER
(
  id            VARCHAR2(36 CHAR) not null,
  db_id         VARCHAR2(36 CHAR) not null,
  jdbc_driver   VARCHAR2(50 CHAR) not null,
  jdbc_password VARCHAR2(50 CHAR) not null,
  jdbc_url      VARCHAR2(50 CHAR) not null,
  jdbc_username VARCHAR2(50 CHAR) not null,
  name          VARCHAR2(30 CHAR) not null,
  remark        VARCHAR2(150 CHAR)
)
;
alter table DW_DWLAYER
  add primary key (ID);

prompt Creating DW_HIS_COLMETA...
create table DW_HIS_COLMETA
(
  id                 VARCHAR2(36 CHAR) not null,
  colname            VARCHAR2(30 CHAR) not null,
  coltype            VARCHAR2(30 CHAR) not null,
  columnmeta_id      VARCHAR2(36 CHAR) not null,
  comments           VARCHAR2(100 CHAR),
  defaultvalue       VARCHAR2(50 CHAR),
  his_createdate     TIMESTAMP(6),
  history_id         VARCHAR2(36 CHAR) not null,
  name               VARCHAR2(30 CHAR),
  nullable           NUMBER(1),
  reasons            VARCHAR2(200 CHAR),
  sorted             NUMBER(10),
  tablemeta_id       VARCHAR2(36 CHAR),
  his_coloperatetype VARCHAR2(36 CHAR) not null
)
;
alter table DW_HIS_COLMETA
  add primary key (ID);

prompt Creating DW_HIS_HISTORY...
create table DW_HIS_HISTORY
(
  id           VARCHAR2(255 CHAR) not null,
  intiactive   NUMBER(1),
  operatetime  TIMESTAMP(6),
  operatetype  VARCHAR2(36 CHAR) not null,
  operater     VARCHAR2(36 CHAR) not null,
  sql_content  VARCHAR2(36 CHAR),
  tablemeta_id VARCHAR2(36 CHAR) not null
)
;
alter table DW_HIS_HISTORY
  add primary key (ID);

prompt Creating DW_HIS_TABMETA...
create table DW_HIS_TABMETA
(
  id             VARCHAR2(36 CHAR) not null,
  classify_id    VARCHAR2(36 CHAR) not null,
  dwlayer_id     VARCHAR2(36 CHAR),
  his_createdate TIMESTAMP(6),
  history_id     VARCHAR2(36 CHAR) not null,
  name           VARCHAR2(30 CHAR),
  remark         VARCHAR2(150 CHAR),
  tablemeta_id   VARCHAR2(36 CHAR) not null,
  tablename      VARCHAR2(30 CHAR) not null
)
;
alter table DW_HIS_TABMETA
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
alter table DW_KPI
  add constraint DFG unique (CODE, KPI_TYPE_ID);

prompt Creating DW_TABLEMETA...
create table DW_TABLEMETA
(
  id          VARCHAR2(36 CHAR) not null,
  classify_id VARCHAR2(36 CHAR) not null,
  dwlayer_id  VARCHAR2(36 CHAR),
  name        VARCHAR2(30 CHAR),
  remark      VARCHAR2(150 CHAR),
  tablename   VARCHAR2(30 CHAR) not null
)
;
alter table DW_TABLEMETA
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

prompt Disabling triggers for DW_CD_COLCLASSIFY...
alter table DW_CD_COLCLASSIFY disable all triggers;
prompt Disabling triggers for DW_CD_COLDEFINE...
alter table DW_CD_COLDEFINE disable all triggers;
prompt Disabling triggers for DW_CLASSIFY...
alter table DW_CLASSIFY disable all triggers;
prompt Disabling triggers for DW_COLUMNMETA...
alter table DW_COLUMNMETA disable all triggers;
prompt Disabling triggers for DW_CONSTRAINTS...
alter table DW_CONSTRAINTS disable all triggers;
prompt Disabling triggers for DW_CONS_COLS...
alter table DW_CONS_COLS disable all triggers;
prompt Disabling triggers for DW_DB...
alter table DW_DB disable all triggers;
prompt Disabling triggers for DW_DWLAYER...
alter table DW_DWLAYER disable all triggers;
prompt Disabling triggers for DW_HIS_COLMETA...
alter table DW_HIS_COLMETA disable all triggers;
prompt Disabling triggers for DW_HIS_HISTORY...
alter table DW_HIS_HISTORY disable all triggers;
prompt Disabling triggers for DW_HIS_TABMETA...
alter table DW_HIS_TABMETA disable all triggers;
prompt Disabling triggers for DW_KPI...
alter table DW_KPI disable all triggers;
prompt Disabling triggers for DW_TABLEMETA...
alter table DW_TABLEMETA disable all triggers;
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
prompt Loading DW_CD_COLCLASSIFY...
insert into DW_CD_COLCLASSIFY (id, db_id, deleted, name, remark)
values ('1223a9b5-1979-4fa7-90fa-882731a29e8e', '15b82b91-1d66-44fa-9b97-b3c84a2ffc5f', 0, '默认分类', null);
commit;
prompt 1 records loaded
prompt Loading DW_CD_COLDEFINE...
prompt Table is empty
prompt Loading DW_CLASSIFY...
insert into DW_CLASSIFY (id, dwlayer_id, name, parent_id, remark)
values ('b38fb1e2-52f3-41b3-acae-1ea00765d768', 'c7c280b2-af77-4b34-9898-905d7fc4d2c6', '默认分类', null, null);
commit;
prompt 1 records loaded
prompt Loading DW_COLUMNMETA...
insert into DW_COLUMNMETA (id, colname, coltype, comments, defaultvalue, name, nullable, reasons, sorted, tablemeta_id)
values ('adb585dd-e4b6-4d32-964b-52a08c8edf57', '22', 'NUMBER(P,S)', '22', '22', '22', 0, null, 1, 'f9e554d6-5600-47f5-95f7-08d924c558ea');
insert into DW_COLUMNMETA (id, colname, coltype, comments, defaultvalue, name, nullable, reasons, sorted, tablemeta_id)
values ('582bfe02-e77c-45f9-ad98-c8e9fe3f7ed8', '44', 'NCHAR(N)', null, '444', '44', 0, null, 2, 'f9e554d6-5600-47f5-95f7-08d924c558ea');
insert into DW_COLUMNMETA (id, colname, coltype, comments, defaultvalue, name, nullable, reasons, sorted, tablemeta_id)
values ('7d8aca39-8a95-42dd-9c43-ed9d5c29caa5', 'dfgd', 'NCHAR(N)', null, 'dfgdg', 'dfgdfg', 0, null, 3, 'f9e554d6-5600-47f5-95f7-08d924c558ea');
insert into DW_COLUMNMETA (id, colname, coltype, comments, defaultvalue, name, nullable, reasons, sorted, tablemeta_id)
values ('dfd107a0-8191-4b94-aedb-995405018066', '111', 'CHAR(N)', '1111', '1111', '你好', 1, '1111', 1, '46ca3ee2-4e3e-45fd-a44e-a908878a82fe');
commit;
prompt 4 records loaded
prompt Loading DW_CONSTRAINTS...
insert into DW_CONSTRAINTS (id, createtime, creater, name, tablemeta_id, type)
values ('5708a323-3787-4ffb-8b01-4ee20f735e05', to_timestamp('10-08-2017 16:09:33.195000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', 'ff', 'f9e554d6-5600-47f5-95f7-08d924c558ea', 'Unique');
commit;
prompt 1 records loaded
prompt Loading DW_CONS_COLS...
insert into DW_CONS_COLS (id, col_id, constraints_id, ref_col_id, ref_table_id)
values ('9b4e9cf0-543f-4ed1-b2a7-9fd9bcef575f', 'adb585dd-e4b6-4d32-964b-52a08c8edf57', '5708a323-3787-4ffb-8b01-4ee20f735e05', null, null);
insert into DW_CONS_COLS (id, col_id, constraints_id, ref_col_id, ref_table_id)
values ('58bac150-00e7-46e6-bfa4-1f7ec60c7d35', '582bfe02-e77c-45f9-ad98-c8e9fe3f7ed8', '5708a323-3787-4ffb-8b01-4ee20f735e05', null, null);
commit;
prompt 2 records loaded
prompt Loading DW_DB...
insert into DW_DB (id, dbtype, name, remark)
values ('15b82b91-1d66-44fa-9b97-b3c84a2ffc5f', 'oracle', 'dw', null);
commit;
prompt 1 records loaded
prompt Loading DW_DWLAYER...
insert into DW_DWLAYER (id, db_id, jdbc_driver, jdbc_password, jdbc_url, jdbc_username, name, remark)
values ('c7c280b2-af77-4b34-9898-905d7fc4d2c6', '15b82b91-1d66-44fa-9b97-b3c84a2ffc5f', 'oracle.jdbc.driver.OracleDriver', 'xx', 'jdbc:oracle:thin:@<host>:<port>:<SID>', 'xx', 'ods', null);
commit;
prompt 1 records loaded
prompt Loading DW_HIS_COLMETA...
insert into DW_HIS_COLMETA (id, colname, coltype, columnmeta_id, comments, defaultvalue, his_createdate, history_id, name, nullable, reasons, sorted, tablemeta_id, his_coloperatetype)
values ('60c0c357-b9f2-4cb3-9916-5336ac04947f', '11', 'CHAR(N)', 'a98f8bbd-625d-4995-bea0-46f7b6c7d2f3', '11', '11', to_timestamp('10-08-2017 15:14:41.884000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1502349272222', '11', 1, null, 1, 'f9e554d6-5600-47f5-95f7-08d924c558ea', 'delete_col');
insert into DW_HIS_COLMETA (id, colname, coltype, columnmeta_id, comments, defaultvalue, his_createdate, history_id, name, nullable, reasons, sorted, tablemeta_id, his_coloperatetype)
values ('8789885a-f7c4-4c6c-8141-ff0188a7a134', '22', 'NUMBER(P,S)', 'adb585dd-e4b6-4d32-964b-52a08c8edf57', '22', '22', to_timestamp('10-08-2017 15:18:09.020000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1502349272222', '22', 0, null, 1, 'f9e554d6-5600-47f5-95f7-08d924c558ea', 'create_col');
insert into DW_HIS_COLMETA (id, colname, coltype, columnmeta_id, comments, defaultvalue, his_createdate, history_id, name, nullable, reasons, sorted, tablemeta_id, his_coloperatetype)
values ('cfc6674f-744a-4e3b-8b41-ed794678729d', '44', 'NCHAR(N)', '582bfe02-e77c-45f9-ad98-c8e9fe3f7ed8', null, '444', to_timestamp('10-08-2017 16:09:12.979000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1502349272222', '44', 0, null, 2, 'f9e554d6-5600-47f5-95f7-08d924c558ea', 'create_col');
insert into DW_HIS_COLMETA (id, colname, coltype, columnmeta_id, comments, defaultvalue, his_createdate, history_id, name, nullable, reasons, sorted, tablemeta_id, his_coloperatetype)
values ('173c906f-5be1-4612-95fa-603ecf9affe5', 'dfgd', 'NCHAR(N)', '7d8aca39-8a95-42dd-9c43-ed9d5c29caa5', null, 'dfgdg', to_timestamp('10-08-2017 16:09:21.096000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1502349272222', 'dfgdfg', 0, null, 3, 'f9e554d6-5600-47f5-95f7-08d924c558ea', 'create_col');
commit;
prompt 4 records loaded
prompt Loading DW_HIS_HISTORY...
insert into DW_HIS_HISTORY (id, intiactive, operatetime, operatetype, operater, sql_content, tablemeta_id)
values ('1502349272222', 1, to_timestamp('10-08-2017 15:14:41.884000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'update_table', 'admin', null, 'f9e554d6-5600-47f5-95f7-08d924c558ea');
commit;
prompt 1 records loaded
prompt Loading DW_HIS_TABMETA...
insert into DW_HIS_TABMETA (id, classify_id, dwlayer_id, his_createdate, history_id, name, remark, tablemeta_id, tablename)
values ('b416b7e4-6546-494e-affd-9c58060c3a6f', 'b38fb1e2-52f3-41b3-acae-1ea00765d768', null, to_timestamp('10-08-2017 15:18:09.020000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1502349272222', null, null, 'f9e554d6-5600-47f5-95f7-08d924c558ea', 'test1');
insert into DW_HIS_TABMETA (id, classify_id, dwlayer_id, his_createdate, history_id, name, remark, tablemeta_id, tablename)
values ('4ac5ba81-85a6-4004-8e27-04e16600b35f', 'b38fb1e2-52f3-41b3-acae-1ea00765d768', null, to_timestamp('10-08-2017 15:18:31.681000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1502349272222', '222', null, 'f9e554d6-5600-47f5-95f7-08d924c558ea', 'test1');
insert into DW_HIS_TABMETA (id, classify_id, dwlayer_id, his_createdate, history_id, name, remark, tablemeta_id, tablename)
values ('47534389-313e-429b-bdb1-10f4c6697a7d', 'b38fb1e2-52f3-41b3-acae-1ea00765d768', null, to_timestamp('10-08-2017 15:18:57.229000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1502349272222', '222', null, 'f9e554d6-5600-47f5-95f7-08d924c558ea', 'test1');
insert into DW_HIS_TABMETA (id, classify_id, dwlayer_id, his_createdate, history_id, name, remark, tablemeta_id, tablename)
values ('37376f69-519a-4c19-8eb8-20670254eef1', 'b38fb1e2-52f3-41b3-acae-1ea00765d768', null, to_timestamp('10-08-2017 16:09:12.979000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1502349272222', '222', null, 'f9e554d6-5600-47f5-95f7-08d924c558ea', 'test1');
insert into DW_HIS_TABMETA (id, classify_id, dwlayer_id, his_createdate, history_id, name, remark, tablemeta_id, tablename)
values ('88b14b24-1766-474c-adb0-d5bd98fe9cd0', 'b38fb1e2-52f3-41b3-acae-1ea00765d768', null, to_timestamp('10-08-2017 16:09:21.096000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1502349272222', '222', null, 'f9e554d6-5600-47f5-95f7-08d924c558ea', 'test1');
commit;
prompt 5 records loaded
prompt Loading DW_KPI...
prompt Table is empty
prompt Loading DW_TABLEMETA...
insert into DW_TABLEMETA (id, classify_id, dwlayer_id, name, remark, tablename)
values ('46ca3ee2-4e3e-45fd-a44e-a908878a82fe', 'b38fb1e2-52f3-41b3-acae-1ea00765d768', 'c7c280b2-af77-4b34-9898-905d7fc4d2c6', '创建历史测试', null, 'logtest');
insert into DW_TABLEMETA (id, classify_id, dwlayer_id, name, remark, tablename)
values ('f9e554d6-5600-47f5-95f7-08d924c558ea', 'b38fb1e2-52f3-41b3-acae-1ea00765d768', 'c7c280b2-af77-4b34-9898-905d7fc4d2c6', '222', null, 'test1');
commit;
prompt 2 records loaded
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
values ('e0165586-f227-48ec-a84e-1a65b7081aa0', null, 1, 'menu', 'dw', null, null, null, '/dwmeta/DwmetaApp.jsp?db_id=15b82b91-1d66-44fa-9b97-b3c84a2ffc5f');
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
values ('0ec73c74-a105-4cd6-840c-f2021acdd6aa', null, 1, 'menu', '元数据综合查询', null, null, 1, null);
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('8bd386e1-781c-44f9-af88-a43623c13c0b', null, 1, 'menu', 'KPI指标库管理', null, null, 0, '/kpi/KpiApp.jsp');
insert into T_MENU (id, code, leaf, menutype, name, parent_id, remark, sort, url)
values ('878fb569-0ef6-400a-afde-655f4aca00df', null, 1, 'menu', '数据库配置', null, null, 0, '/dwmeta/DBApp.jsp');
commit;
prompt 11 records loaded
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
values ('admin', 'a124a5f1-70a3-4dba-a014-68385edd922b');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', 'a7439ed8-ad09-452f-9860-27157c9027d9');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', 'c8f4411b-6f80-4bbd-ba84-cc711af80a72');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', 'ca51fe5d-6e24-4388-8249-f8f1ac6f4feb');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', 'e0165586-f227-48ec-a84e-1a65b7081aa0');
insert into T_ROLE_MENU (role_id, menu_id)
values ('admin', 'e35d2b7f-58c2-4e61-a50d-bc6112779b4c');
commit;
prompt 11 records loaded
prompt Loading T_USER...
insert into T_USER (id, candel, email, lastlogintime, loginname, mobile, name, phone, pwd, remark, state)
values ('admin', 0, null, to_timestamp('10-08-2017 15:08:00.085000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', null, 'admin', null, 'admin', null, 1);
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
prompt Enabling triggers for DW_CD_COLCLASSIFY...
alter table DW_CD_COLCLASSIFY enable all triggers;
prompt Enabling triggers for DW_CD_COLDEFINE...
alter table DW_CD_COLDEFINE enable all triggers;
prompt Enabling triggers for DW_CLASSIFY...
alter table DW_CLASSIFY enable all triggers;
prompt Enabling triggers for DW_COLUMNMETA...
alter table DW_COLUMNMETA enable all triggers;
prompt Enabling triggers for DW_CONSTRAINTS...
alter table DW_CONSTRAINTS enable all triggers;
prompt Enabling triggers for DW_CONS_COLS...
alter table DW_CONS_COLS enable all triggers;
prompt Enabling triggers for DW_DB...
alter table DW_DB enable all triggers;
prompt Enabling triggers for DW_DWLAYER...
alter table DW_DWLAYER enable all triggers;
prompt Enabling triggers for DW_HIS_COLMETA...
alter table DW_HIS_COLMETA enable all triggers;
prompt Enabling triggers for DW_HIS_HISTORY...
alter table DW_HIS_HISTORY enable all triggers;
prompt Enabling triggers for DW_HIS_TABMETA...
alter table DW_HIS_TABMETA enable all triggers;
prompt Enabling triggers for DW_KPI...
alter table DW_KPI enable all triggers;
prompt Enabling triggers for DW_TABLEMETA...
alter table DW_TABLEMETA enable all triggers;
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
