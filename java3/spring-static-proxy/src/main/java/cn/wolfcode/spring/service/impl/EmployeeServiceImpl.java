package cn.wolfcode.spring.service.impl;

import cn.wolfcode.spring.service.IEmployeeService;

// 真实类， 真实对象， 房东
public class EmployeeServiceImpl implements IEmployeeService {
	@Override
	public void save(String username, String password) {
		System.out.println("保存" + username + ":" + password); // 业务操作
	}
}
