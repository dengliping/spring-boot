package com.spring.dto;

public class PageResponse<T> {

	private int count;   //记录总数
	private T content;   //查询的内容
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
}
