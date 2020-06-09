package cn.wolfcode.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 城市对象
 */
public class City {
	private Long id;
	private String name;

	public City() {
	}

	public City(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * 根据省份id查询省份中的城市!
	 * @return
	 */
	public static List<City> getCityByProvinceId(Long pid) {
		
		List<City> citys = new ArrayList<City>();
		
		if (pid == 1) {
			citys = Arrays.asList(
					new City(11L,"长沙市"),
					new City(12L,"株洲市"),
					new City(13L,"湘潭市"),
					new City(14L,"衡阳市"),
					new City(15L,"邵阳市"),
					new City(16L,"岳阳市"),
					new City(17L,"常德市"),
					new City(18L,"张家界市")
			);
		} else if (pid == 2) {
			citys = Arrays.asList(
					new City(21L,"广州市"),
					new City(22L,"深圳市"),
					new City(23L,"佛山市"),
					new City(24L,"中山市"),
					new City(25L,"珠海市"),
					new City(26L,"汕头市"),
					new City(27L,"潮州市"),
					new City(28L,"东莞市")
			);
		} else if (pid == 3) {
			citys = Arrays.asList(
					new City(31L,"孝感市"),
					new City(32L,"黄冈市"),
					new City(33L,"咸宁市"),
					new City(34L,"恩施州"),
					new City(35L,"鄂州市"),
					new City(36L,"武汉市"),
					new City(37L,"荆门市"),
					new City(38L,"襄阳市")
			);
		}
		return citys;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "City [id=" + id + ", name=" + name + "]";
	}
}
