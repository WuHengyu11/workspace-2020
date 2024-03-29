package cn.wolfcode.rbac.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wolfcode.rbac.service.IAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applciationContext.xml")
public class AccountTest {
	
	@Autowired
	private IAccountService accountService;
	
	@Test
	public void testTransfer() throws Exception {
		accountService.transfer(1L, 2L, new BigDecimal(200));
	}
}
