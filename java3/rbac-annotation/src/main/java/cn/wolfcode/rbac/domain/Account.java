package cn.wolfcode.rbac.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Account {
	private Long id;
	private BigDecimal balance;
}
