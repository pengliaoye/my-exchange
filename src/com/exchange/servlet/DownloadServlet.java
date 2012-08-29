package com.exchange.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 4596865066358608047L;
	private static Logger log = LoggerFactory.getLogger(DownloadServlet.class);

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		log.info(path);
		File file = new File(request.getSession().getServletContext()
				.getRealPath(path));
		String fileName = file.getName();
		if (!file.exists()) {
			log.error("the file is not found, filePath: " + path);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		String agent = request.getHeader("USER-AGENT");
		response.setHeader("Content-disposition", "attachment;filename="
				+ encode(fileName, agent));

		InputStream in = new FileInputStream(file);
		OutputStream out = response.getOutputStream();
		try {
			IOUtils.copy(in, out);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	private String encode(String fileName, String agent) throws IOException {
		if (agent != null && agent.indexOf("MSIE") >= 0) {
			return URLEncoder.encode(fileName, "UTF8");
		}

		if (agent != null && agent.indexOf("Mozilla") >= 0) {
			return "=?UTF-8?B?"
					+ new String(
							Base64.encodeBase64(fileName.getBytes("UTF-8")))
					+ "?=";
		}

		return fileName;
	}

}
