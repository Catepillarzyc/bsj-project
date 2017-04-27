/**
 * @author shiqp
 * @date 2016-03-21
 */
package com.questionnaire.pojo.dto;

import java.util.List;

public class PageDto<T> {
	
	private Integer pageSize;  //分页长度
	private Integer startIndex;//分页起始�?
	private Integer currentPage;//当前�?
	private Integer totalPage;//总页�?
	private List<T> records; //该页的所有记�?
	private Integer totalRecords; //记录总数
	private String sort;//排序字段
	private String rule; //排序规则
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getStartIndex() {
		if(startIndex == null){
			startIndex = (this.currentPage-1) * this.pageSize;
		}
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		if(currentPage != null){
			this.currentPage = currentPage;
		}else{
			this.currentPage = 1;
		}
	}
	public Integer getTotalPage() {
		if(totalPage == null){
			if(this.totalRecords%this.pageSize != 0){
				totalPage = this.totalRecords/this.pageSize + 1;
			}else{
				totalPage = this.totalRecords/this.pageSize;
			}
		}
		if(totalPage == 0){
			totalPage = 1;
		}
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
		getTotalPage();
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	

}
