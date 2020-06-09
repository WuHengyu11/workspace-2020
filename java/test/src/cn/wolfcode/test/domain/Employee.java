package cn.wolfcode.test.domain;

import java.math.BigDecimal;

public class Employee {
	private Long id;
	private String name;
	private String email;
	private Integer age;
	private BigDecimal salary;
	
	public Employee() {
		super();
	}

	public Employee(Long id, String name, String email, Integer age, BigDecimal salary) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + ", salary=" + salary
				+ "]";
	}
	
	
}
