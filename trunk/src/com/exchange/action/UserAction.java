package com.exchange.action;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.exchange.domain.User;
import com.exchange.service.UserService;
import com.exchange.utils.ExcelUtil;

public class UserAction {
	private UserService userService;
	private List<User> list;
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}
	public String execute(){
		list=userService.getAllUser(-1, -1, null, false);
		return "login";
	}
	public String export2excel(){
		list=userService.getAllUser(-1, -1, null, false);
		String[] titles={"ID","用户名","密码","帐户没有失效","帐户没有锁定","凭证没有过期","是否可用"};
		String[] fields={"id","userName","passWord","accountNonExpired","accountNonLocked","credentialsNonExpired","enabled"};	
		String sheetName="用户信息";
		HSSFWorkbook excel=ExcelUtil.createExcel(list, titles, fields, sheetName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = sdf.format(new Date()) + ".xls";
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("application/x-msexcel;charset=UTF-8");
		response.setHeader("Content-Disposition","attachment; filename="
				+ fileName);
		try {
			excel.write(response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String exportExcel(){
		list=userService.getAllUser(-1, -1, null, false);
		String[] titles={"ID","用户名","密码","帐户没有失效","帐户没有锁定","凭证没有过期","是否可用"};
		String[] fields={"id","userName","passWord","accountNonExpired","accountNonLocked","credentialsNonExpired","enabled"};	
		String sheetName="用户信息";
		HttpServletResponse response=ServletActionContext.getResponse();
		ExcelUtil.exportExcel(list, titles, fields, sheetName, response);
		return null;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
