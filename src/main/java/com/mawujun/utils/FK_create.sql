########################################这是添加外键约束的自动生成
alter table dw_classify drop constraint fk_dw_classify_dwlayer_id
alter table dw_classify add constraint fk_dw_classify_dwlayer_id foreign key (dwlayer_id) references dw_dwlayer (id);
alter table dw_classify drop constraint fk_dw_classify_parent_id
alter table dw_classify add constraint fk_dw_classify_parent_id foreign key (parent_id) references dw_classify (id);
########################################结束
