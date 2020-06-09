package cn.wolfcode.javaweb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class Product {
	private Long id;
	private String productName;
	private Long dir_id;
	private double salePrice;
	private String supplier;
	private String brand;
	private double cutoff;
	private double costPrice;
}
