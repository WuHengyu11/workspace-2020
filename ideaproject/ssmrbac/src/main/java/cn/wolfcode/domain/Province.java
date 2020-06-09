package cn.wolfcode.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 省份对象
 */
public class Province {

	private Long id;//id
	private String name;//省份名字
	public Province() {
	}
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Province(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	//获取所有的的省份信息
	public static List<Province> getAllProvince() {
		List<Province> provinces = new ArrayList<Province>();
		provinces.add(new Province(1L, "湖南"));
		provinces.add(new Province(2L, "广东"));
		provinces.add(new Province(3L, "湖北"));
		return provinces;
	}
	public String toString() {
		return "Province [id=" + id + ", name=" + name + "]";
	}

}
