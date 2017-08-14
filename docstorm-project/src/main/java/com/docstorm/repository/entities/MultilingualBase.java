package com.docstorm.repository.entities;

import java.util.List;

public class MultilingualBase<T> {
	private List<T> transInfoList;

	public List<T> getTransInfoList() {
		return transInfoList;
	}

	public void setTransInfoList(List<T> transInfoList) {
		this.transInfoList = transInfoList;
	}

}
