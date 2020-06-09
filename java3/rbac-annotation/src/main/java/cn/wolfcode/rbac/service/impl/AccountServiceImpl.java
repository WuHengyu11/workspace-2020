package cn.wolfcode.rbac.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wolfcode.rbac.mapper.AccountMapper;
import cn.wolfcode.rbac.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	@Transactional
	public void transfer(Long outId, Long inId, BigDecimal amount) {
		accountMapper.substractBalance(outId, amount);
		int i = 1 /0;//模拟停电
		accountMapper.addBalance(inId, amount);
	}

}
