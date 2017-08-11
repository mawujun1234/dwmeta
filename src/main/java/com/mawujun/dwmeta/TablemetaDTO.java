package com.mawujun.dwmeta;

import java.util.List;

public class TablemetaDTO {
	private Tablemeta tablemeta;
	private List<Columnmeta> createcoles;
	private List<Columnmeta> updatecoles;
	private List<Columnmeta> deletecoles;
	private List<ConstraintsVO> createConstraints;
	private List<Constraints> deleteConstraints;
	
	public Tablemeta getTablemeta() {
		return tablemeta;
	}
	public void setTablemeta(Tablemeta tablemeta) {
		this.tablemeta = tablemeta;
	}
	public List<Columnmeta> getCreatecoles() {
		return createcoles;
	}
	public void setCreatecoles(List<Columnmeta> createcoles) {
		this.createcoles = createcoles;
	}
	public List<Columnmeta> getUpdatecoles() {
		return updatecoles;
	}
	public void setUpdatecoles(List<Columnmeta> updatecoles) {
		this.updatecoles = updatecoles;
	}
	public List<Columnmeta> getDeletecoles() {
		return deletecoles;
	}
	public void setDeletecoles(List<Columnmeta> deletecoles) {
		this.deletecoles = deletecoles;
	}
	public List<ConstraintsVO> getCreateConstraints() {
		return createConstraints;
	}
	public void setCreateConstraints(List<ConstraintsVO> createConstraints) {
		this.createConstraints = createConstraints;
	}
	public List<Constraints> getDeleteConstraints() {
		return deleteConstraints;
	}
	public void setDeleteConstraints(List<Constraints> deleteConstraints) {
		this.deleteConstraints = deleteConstraints;
	}
	
	

}
