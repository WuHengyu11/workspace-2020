package cn.wolfcode.luowowo.search.repository;

import cn.wolfcode.luowowo.search.domain.DestinationEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationEsRepository extends ElasticsearchRepository<DestinationEs, String>{

    /**
     * 通过name查询
     * @param name
     * @return
     */
    DestinationEs findByName(String name);
}
