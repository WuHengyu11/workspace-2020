package cn.wolfcode.javaweb.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 封装前台用户传入的数据
 * @author 
 *
 */
@Getter@Setter
public class QueryObject {
	private int currentPage = 1;//默认当前页为1
	private int pageSize = 5;//默认每页显示5条数据
	
	/**
	 * 用于返回开始分页索引
	 * @return
	 */
	public int getStart(){
		return (currentPage - 1) * pageSize;
	}
}
