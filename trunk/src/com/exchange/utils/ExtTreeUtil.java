package com.exchange.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class ExtTreeUtil {
	public JSONObject createTree(List list) {
		JSONObject root = null;
		try {
			root = new JSONObject();
			root.put("id", "-1");
			root.put("text", "全部");
			root.put("leaf", false);
			root.put("describe", "根节点");
			Iterator iterator = list.iterator();
			JSONObject parentNode = null;
			JSONObject nearParentNode = null;
			String pid = "";
			while (iterator.hasNext()) {
				Map map = (Map)iterator.next();
				String id = (String) map.get("id");
				String parentid = (String) map.get("parentid");
				String text=(String)map.get("text");
				String strLeaf = (String) map.get("leaf");
				String describe="";
				Object obj = map.get("describe");
				if(obj!=null){
					describe=(String)map.get("describe");
				}
				boolean leaf=BooleanUtils.toBoolean(strLeaf, "1", "0");
				JSONObject node = new JSONObject();// 设置节点信息
				node.put("id", id);
				node.put("parentid", parentid);
				node.put("text", text);
				node.put("leaf", leaf);
				node.put("describe", describe);
				// 如果父节点为根节点
				if (root.get("id").equals(parentid)) {
					parentNode = root;
				} else if (pid.equals(parentid)) {// 最近一次循环的父节点
					parentNode = nearParentNode;
				} else {
					parentNode = findNode(root, parentid);// 查找父节点
					nearParentNode = parentNode;
					pid = parentid;
				}
				if (parentNode == null) {
					continue;
				}
				if (parentNode != null) {
					if (parentNode.has("children")) {
						parentNode.getJSONArray("children").add(node);
					} else {
						parentNode.put("children", new JSONArray().add(node));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
	}

	/**
	 * 查询节点
	 * 
	 * @param root
	 * @param id
	 * @return
	 */
	public JSONObject findNode(JSONObject root, String id) {
		JSONObject result = null;
		JSONArray children = null;
		result = findNodeNear(root, id);
		if (result != null) {
			return result;
		}
		children = findChildNodes(root);
		try {
			if (children != null && children.size() > 0) {
				for (int i = 0; i < children.size(); i++) {
					JSONObject jso = children.getJSONObject(i);
					JSONObject childFind = findNode(jso, id);
					if (childFind != null) {
						return childFind;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 在父节点的一级子节点中查询孩子节点
	 * 
	 * @param parentnode
	 * @param id
	 * @return
	 */
	public JSONObject findNodeNear(JSONObject parentnode, String id) {
		if (parentnode == null || id == null) {
			return null;
		}
		try {
			JSONArray children = findChildNodes(parentnode);
			if (children == null) {
				return null;
			}
			JSONObject child = null;
			if (children.size() > 0) {
				for (int i = 0; i < children.size(); i++) {
					child = children.getJSONObject(i);
					if (id.equals(child.get("id"))) {
						return child;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取得父节点的所有孩子节点
	 * 
	 * @param parentNode
	 * @return
	 */
	public JSONArray findChildNodes(JSONObject parentNode) {
		if (parentNode == null) {
			return null;
		}
		if (parentNode.has("children")) {
			try {
				return parentNode.getJSONArray("children");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
