package com.exchange.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 2022425104466592026L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		File file = new File("d:\\temp\\y.sql");
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		InputStream is = req.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);

		byte[] buff = new byte[32 * 1024];
		int len;
		while ((len = bis.read(buff)) > 0) {
			bos.write(buff, 0, len);
		}
		bos.flush();
		bis.close();
		bos.close();
		resp.getWriter().print("success");
	}
}
