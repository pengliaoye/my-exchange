package com.exchange.service;

import com.exchange.dao.BaseDao;

public class FileUploadServiceImpl implements FileUploadService {
	private BaseDao fileUploadDao;
	
	public BaseDao getFileUploadDao() {
		return fileUploadDao;
	}
	public void setFileUploadDao(BaseDao fileUploadDao) {
		this.fileUploadDao = fileUploadDao;
	}

	@Override
	public void saveFile(Object object) {
		String sql="insert into testlab(b) values(?);";
		Object[] obj=new Object[]{object};
		fileUploadDao.update(sql,obj);
	}
}
