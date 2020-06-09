package cn.wolfcode.mybatis.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Student {
	private Long id;
	private String name;
	private List<Teacher> teachers = new ArrayList<>();
}
