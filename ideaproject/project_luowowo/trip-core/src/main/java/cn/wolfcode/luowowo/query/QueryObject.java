package cn.wolfcode.luowowo.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Setter
@Getter
public class QueryObject implements Serializable {

    private int currentPage = 1;//当前页
    private int pageSize = 10;//页面显示数据数量
    private String keyword;//搜索关键字
    /*
    private Pageable pageable;  //分页设置对象
    public Pageable getPageable(){
        if(pageable == null){
            //没有指定分页对象值, 默认id倒序
            return  PageRequest.of(currentPage - 1, pageSize,
                    Sort.Direction.ASC, "_id");
        }
        return pageable;
    }
     */

    public String getKeyword(){
        return StringUtils.hasLength(keyword)? keyword : null;
    }
}
