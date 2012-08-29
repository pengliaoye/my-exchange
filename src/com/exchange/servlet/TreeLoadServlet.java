package com.exchange.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONArray;

public class TreeLoadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("label", "level1-1");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("label", "level1-2");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("type", "TreeView");
		map.put("loadOnDemand", "true");
		map.put("label", "Another tree");
		list.add(map);
		JSONArray jsoArr = new JSONArray(list);
		resp.getWriter().print(jsoArr.toString());
		super.doPost(req, resp);
	}

}
