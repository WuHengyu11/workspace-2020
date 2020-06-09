package cn.wolfcode.rbac.service;

import java.math.BigDecimal;

public interface IAccountService {
	/**
	 * 转账
	 * @param outId 出钱的账户
	 * @param inId 进钱的账户
	 * @param amount 转多少钱
	 */
	void transfer(Long outId, Long inId, BigDecimal amount);
}
