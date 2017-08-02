########################################这是添加外键约束的自动生成
--alter table t_constantitem drop constraint fk_t_constantitem_constant_id;
alter table t_constantitem add constraint fk_t_constantitem_constant_id foreign key (constant_id) references t_constant (id);
--alter table dw_classify drop constraint fk_dw_classify_dwlayer_id;
alter table dw_classify add constraint fk_dw_classify_dwlayer_id foreign key (dwlayer_id) references dw_dwlayer (id);
--alter table dw_classify drop constraint fk_dw_classify_parent_id;
alter table dw_classify add constraint fk_dw_classify_parent_id foreign key (parent_id) references dw_classify (id);
--alter table dw_columnmeta drop constraint fk_dw_columnmeta_tablemeta_id;
alter table dw_columnmeta add constraint fk_dw_columnmeta_tablemeta_id foreign key (tablemeta_id) references dw_tablemeta (id);
--alter table dw_kpi drop constraint fk_dw_kpi_kpi_type_id;
alter table dw_kpi add constraint fk_dw_kpi_kpi_type_id foreign key (kpi_type_id) references t_constantitem (id);
--alter table dw_tablemeta drop constraint fk_dw_tablemeta_classify_id;
alter table dw_tablemeta add constraint fk_dw_tablemeta_classify_id foreign key (classify_id) references dw_classify (id);
--alter table hr_org drop constraint fk_hr_org_orgtype_id;
alter table hr_org add constraint fk_hr_org_orgtype_id foreign key (orgtype_id) references t_constantitem (id);
--alter table hr_position drop constraint fk_hr_position_org_id;
alter table hr_position add constraint fk_hr_position_org_id foreign key (org_id) references hr_org (id);
--alter table hr_position drop constraint fk_hr_position_positiontype_id;
alter table hr_position add constraint fk_hr_position_positiontype_id foreign key (positiontype_id) references t_constantitem (id);
--alter table t_menu drop constraint fk_t_menu_parent_id;
alter table t_menu add constraint fk_t_menu_parent_id foreign key (parent_id) references t_menu (id);
--alter table t_role drop constraint fk_t_role_parent_id;
alter table t_role add constraint fk_t_role_parent_id foreign key (parent_id) references t_role (id);
########################################结束
