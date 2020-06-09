package cn.wolfcode.luowowo.search.service;

import cn.wolfcode.luowowo.search.query.SearchQueryObject;
import org.springframework.data.domain.Page;

import java.util.ArrayList;

/**
 * 所有es公共服务
 */
public interface ISearchService {

    /**
     * 全文搜索 + 高亮显示
     * @param index 索引
     * @param type  类型
     * @param clz
     * @param qo
     * @param fields
     * @param <T>
     * @return 带有分页的全文搜索(高亮显示)结果集
     */
    <T> Page<T>  searchWithHighlight(String index, String type,Class<T> clz,
                                     SearchQueryObject qo, String... fields);

    //泛型方法定义
    //<T> Page<T>  searchWithHighlight2(String index, String type, Class<T> clz, SearchQueryObject qo, String...fields );
}
