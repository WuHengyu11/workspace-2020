package cn.wolfcode._04_el;

import java.util.Arrays;
import java.util.Date;

public class Student {
	private Long id;
	private String name;
	private Integer age;
	private String[] favs;
	private Date date;
	
	public Student() {
		super();
	}
	public Student(Long id, String name, Integer age, String[] favs, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.favs = favs;
		this.date = date;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String[] getFavs() {
		return favs;
	}
	public void setFavs(String[] favs) {
		this.favs = favs;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", favs=" + Arrays.toString(favs) + "]";
	}
	
	
	
}
