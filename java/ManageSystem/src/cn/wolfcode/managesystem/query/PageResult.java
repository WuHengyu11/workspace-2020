package cn.wolfcode.managesystem.query;

import java.util.Collections;
import java.util.List;

import lombok.Getter;


@Getter
public class PageResult {
	private int rows;//总条数
	private List<?> result;//查询到的结果集
	private int currentPage;//当前页
	private int pageSize;//每页显示数据条数
	
	private int prevPage;//上一页
	private int nextPage;//下一页
	private int totalPage;//总页数
	
	
	public PageResult(int rows, List<?> result, int currentPage, int pageSize) {
		super();
		this.rows = rows;
		this.result = result;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		
		//计算总页数
		totalPage = rows % pageSize == 0 ?  rows / pageSize : rows / pageSize + 1; 
	
		//计算上一页
		prevPage = currentPage - 1 > 1 ? currentPage - 1 : 1;
		
		//计算下一页
		nextPage = currentPage + 1 < totalPage ? currentPage + 1 : totalPage;
	}
	
	/**
	 * 查询结果为空
	 * @param pageSize
	 * @return
	 */
	public static PageResult empty(int pageSize){
		return new PageResult(0,Collections.EMPTY_LIST,1,pageSize);
	}
}
