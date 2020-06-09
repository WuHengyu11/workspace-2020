package cn.wolfcode.javaweb.query;

import java.util.List;

import lombok.Getter;

/**
 * 封装用户需要的数据
 * @author Administrator
 *
 */
@Getter
public class PageResult {
	
	//用户传递的参数
	private Integer currentPage;//当前页
	private Integer pageSize;//每页最多显示的页数
	
	//查询出来的数据
	private List<?> result;//存放查询出来的结果集数据
	private Integer rows;//总条数
	
	//计算出来的数据
	private Integer totalPage;//总页数
	private Integer prevPage;//上一页页码
	private Integer nextPage;//下一页页码
	
	public PageResult() {
		super();
	}

	public PageResult(Integer currentPage, Integer pageSize, List<?> result, Integer rows) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.result = result;
		this.rows = rows;
		
		//计算
		this.totalPage = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;
		this.prevPage = currentPage > 1 ? currentPage - 1 : 1;
		this.nextPage = currentPage < totalPage ? currentPage + 1 : totalPage;
		
	} 
	
	
}
