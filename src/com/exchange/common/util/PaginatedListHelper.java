package com.exchange.common.util;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

public class PaginatedListHelper implements PaginatedList{
	
	private int fullListSize;
	private List list;
	private int objectsPerPage;
	private int pageNumber;
	private String searchId;
	private String sortCriterion;
	private SortOrderEnum sortDirection;

	public int getFullListSize() {
		return fullListSize;
	}

	public List getList() {
		return list;
	}

	public int getObjectsPerPage() {
		return objectsPerPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public String getSearchId() {
		return searchId;
	}

	public String getSortCriterion() {
		return sortCriterion;
	}

	public SortOrderEnum getSortDirection() {
		return sortDirection;
	}

	public void setFullListSize(int fullListSize) {
		this.fullListSize = fullListSize;
	}

	public void setList(List list) {
		this.list = list;
	}

	public void setObjectsPerPage(int objectsPerPage) {
		this.objectsPerPage = objectsPerPage;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	public void setSortDirection(SortOrderEnum sortDirection) {
		this.sortDirection = sortDirection;
	}

}
