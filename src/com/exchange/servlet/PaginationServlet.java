package com.exchange.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.displaytag.properties.SortOrderEnum;
import org.displaytag.tags.TableTagParameters;

import com.exchange.common.util.PaginatedListHelper;
import com.exchange.domain.Pagination;

public class PaginationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DataSource ds = null;

	@Override
	public void init() throws ServletException {
		InitialContext context = null;
		try {
			context = new InitialContext();
			this.ds = (DataSource) context.lookup("java:comp/env/zxdb_cc_00");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		int fullListSize = 0;
		List list = null;
		int objectsPerPage = 10;
		int pageNumber = 1;
		String searchId = null;
		String sortCriterion = null;
		SortOrderEnum sortDirection = null;
		
		String test=req.getParameter("field");
		String test1=req.getParameter("abc");
		String page = req.getParameter("page");
		sortCriterion = req.getParameter("sort");
		String dir = req.getParameter("dir");
		String[] chks = req.getParameterValues("_chk");
		
		if (page != null) {
			pageNumber = Integer.parseInt(page);
		}
		if ("asc".equals(dir)) {
			sortDirection = SortOrderEnum.ASCENDING;
		} else if ("desc".equals(dir)) {
			sortDirection = SortOrderEnum.DESCENDING;
		}
		String sql = "select intid,strserviceflowno,dtoldopertime,strcustomermem from cc_billinfo";
		Pagination pagination = new Pagination(pageNumber, objectsPerPage);
		pagination.setOrder(sortCriterion == null ? "strcustomermem"
				: sortCriterion, dir);
		pagination.setSql(sql);
		
		QueryRunner runner = new QueryRunner(ds);
		Object obj = null;
		try {
			String pageSql = pagination.getPageSql();
			list = runner.query(pageSql, new MapListHandler());

			String countSql = pagination.getCountSql();
			obj = runner.query(countSql, new ScalarHandler());
			fullListSize = ((BigDecimal) obj).intValue();
			System.out.println(pageSql);
			System.out.println(countSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PaginatedListHelper paginationHelper = new PaginatedListHelper();
		paginationHelper.setFullListSize(fullListSize);
		paginationHelper.setList(list);
		paginationHelper.setObjectsPerPage(objectsPerPage);
		paginationHelper.setPageNumber(pageNumber);
		paginationHelper.setSearchId(searchId);
		paginationHelper.setSortCriterion(sortCriterion);
		paginationHelper.setSortDirection(sortDirection);

		req.setAttribute("resultList", paginationHelper);
		String exportValue = req
				.getParameter(TableTagParameters.PARAMETER_EXPORTING);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

}
