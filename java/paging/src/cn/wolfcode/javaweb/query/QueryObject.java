package cn.wolfcode.javaweb.query;

import lombok.Getter;
import lombok.Setter;

/**
 * f封装用户传过来的参数,sql中需要使用
 * @author Administrator
 *
 */
@Getter@Setter
public class QueryObject {
	private int currentPage = 1;
	private int pageSize = 5;
	
	public int getStart(){
		return (currentPage-1)*pageSize;
	}
}
