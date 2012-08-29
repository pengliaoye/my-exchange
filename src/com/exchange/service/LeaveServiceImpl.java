package com.exchange.service;

import com.exchange.dao.LeaveDaoImpl;
import com.exchange.domain.Leave;

public class LeaveServiceImpl implements LeaveService {
	private LeaveDaoImpl leaveDao;
	public LeaveDaoImpl getLeaveDao() {
		return leaveDao;
	}
	public void setLeaveDao(LeaveDaoImpl leaveDao) {
		this.leaveDao = leaveDao;
	}
	@Override
	public void submitRequest(Leave leave) throws Exception {
		leaveDao.save(leave);
	}
}
