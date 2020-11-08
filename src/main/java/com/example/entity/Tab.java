package com.example.entity;

import java.util.Date;


public class Tab {

	//parent
	private Integer id;
	

	private String tabName;
	

	private String tabDesc;
	

	private boolean idDelete;

	private Date createDate;
	

	private Integer tabOrder;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public String getTabDesc() {
		return tabDesc;
	}

	public void setTabDesc(String tabDesc) {
		this.tabDesc = tabDesc;
	}

	public boolean isIdDelete() {
		return idDelete;
	}

	public void setIdDelete(boolean idDelete) {
		this.idDelete = idDelete;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getTabOrder() {
		return tabOrder;
	}

	public void setTabOrder(Integer tabOrder) {
		this.tabOrder = tabOrder;
	}

	@Override
	public String toString() {
		return "Tab [id=" + id + ", tabName=" + tabName + ", tabDesc=" + tabDesc + ", idDelete=" + idDelete
				+ ", createDate=" + createDate + ", tabOrder=" + tabOrder + "]";
	}
	
}
