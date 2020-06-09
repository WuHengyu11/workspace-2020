package cn.wolfcode.luowowo.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Method;
import java.util.List;

public class DBHelper {
    /**
     * 更新
     * @param template
     * @param query 条件
     * @param source 数据
     * @param fields 更新字段
     */
    public static void update(MongoTemplate template, Query query, Object source, String... fields){
        Update update = new Update();
        Class<?> clz = source.getClass();
        for (String field : fields) {
            try {
                //name    getName
                Method method = clz.getMethod("get" + field.substring(0, 1).toUpperCase()
                        + field.substring(1));
                Object o = method.invoke(source);  //soucre.getName();
                update.set(field, o);  //name  soucre.getName()
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        template.updateMulti(query,update, clz);
    }

    /**
     * 分页
     * @param template
     * @param clz 操作对象
     * @param query 条件
     * @param pageable 分页
     * @param <T>
     * @return
     */
    public static <T> Page<T> query(MongoTemplate template,
                                    Class<T> clz,  Query query, Pageable pageable){
        Long total = template.count(query, clz);
        if(total == 0){
            return  Page.empty();
        }
        query.with(pageable);
        List<T> list = template.find(query, clz);
        return new PageImpl(list, pageable, total);
    }
}