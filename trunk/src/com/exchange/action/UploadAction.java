package com.exchange.action;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 文件上传.
 * 
 * @version 0.1
 * @author Administrator
 * 
 */
public class UploadAction extends ActionSupport {

	private static final long serialVersionUID = 2096441211155437367L;
	private static final Logger logger = LoggerFactory
			.getLogger(UploadAction.class);
	/** 文件数据. */
	private File filedata;
	/** 文件名称. */
	private String filedataFileName;
	/** 文件类型. */
	private String filedataContentType;
	/** 上传目录. */
	private String uploadDir;

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public void setFiledataContentType(String filedataContentType) {
		this.filedataContentType = filedataContentType;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	@Override
	public String execute() throws Exception {

		ServletContext servletContext = ServletActionContext
				.getServletContext();
		String path = servletContext.getRealPath(uploadDir + File.separator
				+ filedataFileName);
		try {
			File destFile = new File(path);
			FileUtils.copyFile(filedata, destFile);
		} catch (Exception e) {
			logger.error("", e);
		}

		return "success";
	}
}
