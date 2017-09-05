package com.mawujun.dwmeta;

import java.util.List;

public class ConstraintsVO1 extends Constraints {
	private String tablemeta_name;
	
	List<ConstraintsColsVO> constraintscols;

	public List<ConstraintsColsVO> getConstraintscols() {
		return constraintscols;
	}

	public void setConstraintscols(List<ConstraintsColsVO> constraintscols) {
		this.constraintscols = constraintscols;
	}

	public String getTablemeta_name() {
		return tablemeta_name;
	}

	public void setTablemeta_name(String tablemeta_name) {
		this.tablemeta_name = tablemeta_name;
	}

}
