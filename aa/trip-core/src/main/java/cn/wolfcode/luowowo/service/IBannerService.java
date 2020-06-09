package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.Banner;
import cn.wolfcode.luowowo.query.BannerQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *banner服务接口
 */
public interface IBannerService {

    /**
     * 查单个
     * @param id
     * @return
     */
    Banner get(String id);

    /**
     * 分页
     * @param qo
     * @return
     */
    Page<Banner> query(BannerQuery qo);

    /**
     * 添加与更新
     * @param banner
     */
    void saveOrUpdate(Banner banner);

    /**
     * 修改热门状态
     * @param id
     * @param hot
     */
    void changeHotValue(String id, int hot);

    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     * 查询banner
     * @param type
     * @return
     */
    List<Banner> queryBanner(int type);
}
