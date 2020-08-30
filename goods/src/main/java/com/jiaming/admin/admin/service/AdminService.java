package com.jiaming.admin.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiaming.entity.Admin;
import com.jiaming.mapper.AdminMapper;

@Service
public class AdminService {
	
	@Autowired
	private AdminMapper adminMapper;

	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		return adminMapper.selectAdmin(admin);
	}
	
	

}
