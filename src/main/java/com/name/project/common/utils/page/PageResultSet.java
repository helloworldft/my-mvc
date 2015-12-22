package com.name.project.common.utils.page;

import java.util.List;

public class PageResultSet<M> {
	
	private List<M> list; // 当前页的数据信息
	private Page page; // 当前页的信息

	
	
	public List<M> getList() {
		return list;
	}
	public void setList(List<M> list) {
		this.list = list;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

}
