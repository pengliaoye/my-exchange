package com.exchange.ws;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetWebService {
	private static final Logger logger = LoggerFactory
			.getLogger(NetWebService.class);

	public String service(String request) {
		Document resp = DocumentHelper.createDocument();
		Element outputInfo = resp.addElement("outputInfo");
		try {
			Document document = null;
			try {
				document = DocumentHelper.parseText(request);
			} catch (DocumentException de) {
				outputInfo.addElement("resultcode").addText("1");
				outputInfo.addElement("errorinfo").addText("文档格式不正确");
				return resp.asXML();
			}
			String ord_sn = document.selectSingleNode("//ord_sn").getText();
			String ord_time = document.selectSingleNode("//ord_time").getText();
			Date ardt = DateUtils.parseDate(ord_time,
					new String[] { "yyyy-MM-dd HH:mm:ss" });
			String latn_id = document.selectSingleNode("//latn_id").getText();
			String deal_type = document.selectSingleNode("//deal_type")
					.getText();
			String user_no = document.selectSingleNode("//user_no").getText();
			String ord_desc = document.selectSingleNode("//ord_desc").getText();
			String rel_man = document.selectSingleNode("//rel_man").getText();
			String rel_tel = document.selectSingleNode("//rel_tel").getText();
			String addr_detail = document.selectSingleNode("//addr_detail")
					.getText();
			String ibss_sn = document.selectSingleNode("//ibss_sn").getText();
			String cc_sn = document.selectSingleNode("//cc_sn").getText();
			String state = document.selectSingleNode("//state").getText();
			String state_time = document.selectSingleNode("//state_time")
					.getText();
			Date statedt = DateUtils.parseDate(state_time,
					new String[] { "yyyy-MM-dd HH:mm:ss" });
			String sql = "insert into agent_info(ord_sn,ord_time,latn_id,deal_type,user_no,ord_desc,rel_man,rel_tel,addr_detail,ibss_sn,cc_sn,state,state_time) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[] params = new Object[] { ord_sn, ardt, latn_id, deal_type,
					user_no, ord_desc, rel_man, rel_tel, addr_detail, ibss_sn,
					cc_sn, state, statedt };
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/zxdb_wf");
			Connection conn = null;
			PreparedStatement ps = null;

			try {
				conn = ds.getConnection();
				conn.setAutoCommit(false);

				ps = conn.prepareStatement(sql);
				bindParams(ps, params);
				ps.executeUpdate();
				if (conn != null) {
					conn.commit();
				}

				outputInfo.addElement("resultcode").addText("0");
				outputInfo.addElement("errorinfo").addText("OK");
				return resp.asXML();

			} catch (SQLException e) {
				if (conn != null) {
					conn.rollback();
				}
				if (e.getMessage().indexOf("ORA-00001") != -1) {
					outputInfo.addElement("resultcode").addText("2");
					outputInfo.addElement("errorinfo").addText("重复录入");
					return resp.asXML();
				} else {
					logger.error(sql);
					logger.error(ExceptionUtils.getStackTrace(e));
					outputInfo.addElement("resultcode").addText("1");
					outputInfo.addElement("errorinfo").addText("参数有误");
					return resp.asXML();
				}
			} finally {
				try {
					if (ps != null) {
						ps.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException sqlEx) {
					logger.error("unhandled exception");
				}
			}
		} catch (Exception e) {
			outputInfo.addElement("resultcode").addText("1");
			outputInfo.addElement("errorinfo").addText("服务端错误");
			logger.error(ExceptionUtils.getStackTrace(e));
			return resp.asXML();
		}
	}

	protected void bindParams(java.sql.PreparedStatement stmt, Object[] list)
			throws Exception {
		if (list == null) {
			return;
		}
		for (int i = 0; i < list.length; i++) {
			Object o = list[i];
			if (o instanceof NULL) {
				stmt.setNull(i + 1, ((NULL) o).getSqltype());
			} else if (o instanceof String) {
				stmt.setString(i + 1, (String) o);
			} else if (o instanceof Integer) {
				stmt.setInt(i + 1, ((Integer) o).intValue());
			} else if (o instanceof Long) {
				stmt.setLong(i + 1, ((Long) o).longValue());
			} else if (o instanceof Short) {
				stmt.setShort(i + 1, ((Short) o).shortValue());
			} else if (o instanceof Float) {
				stmt.setFloat(i + 1, ((Float) o).floatValue());
			} else if (o instanceof Double) {
				stmt.setDouble(i + 1, ((Double) o).doubleValue());
			} else if (o instanceof java.sql.Date) {
				stmt.setDate(i + 1, (java.sql.Date) o);
			} else if (o instanceof java.sql.Time) {
				stmt.setTime(i + 1, (java.sql.Time) o);
			} else if (o instanceof java.util.Date) {
				Timestamp timestamp = new Timestamp(((java.util.Date) o)
						.getTime());
				stmt.setTimestamp(i + 1, timestamp);
			} else if (o instanceof java.sql.Timestamp) {
				stmt.setTimestamp(i + 1, (java.sql.Timestamp) o);
			}
		}
	}
}
