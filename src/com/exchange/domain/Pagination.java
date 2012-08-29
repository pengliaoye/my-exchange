package com.exchange.domain;

public class Pagination {
	private int pageIndex;
	private int pageSize;
	private String sortCriterion;
	private String sortDirection;
	private String sql;

	public Pagination(int pageIndex, int pageSize) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public Pagination(int pageIndex, int pageSize, String sql) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.sql = sql;
	}

	public void setOrder(String sortCriterion, String sortDirection) {
		this.sortCriterion = sortCriterion;
		this.sortDirection = sortDirection;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getCountSql() {
		StringBuilder builder = new StringBuilder();
		builder.append("select count(*) from( ");
		builder.append(this.sql);
		builder.append(" )");
		String countSql = builder.toString();
		return countSql;
	}

	public String getPageSql() {
		int beginRow = (pageIndex - 1) * pageSize + 1;
		int endRow = pageIndex * pageSize;

		StringBuilder builder = new StringBuilder();
		builder.append("select *\n");
		builder.append("from \n");
		builder.append("	(select rownum rn,a.*\n");
		builder.append("	from(\n");
		builder.append(this.sql);
		if (this.sortCriterion != null) {
			builder.append("\norder by ");
			builder.append(this.sortCriterion);
			if (this.sortDirection != null) {
				builder.append(" "+this.sortDirection);
			}
		}
		builder.append("	) a \n");
		builder.append("	where rownum<=");
		builder.append(endRow);
		builder.append(")\n	where rn>=");
		builder.append(beginRow);
		String pageSql = builder.toString();
		return pageSql;
	}
}
