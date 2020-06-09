package cn.wolfcode.luowowo.search.repository;

import cn.wolfcode.luowowo.search.domain.StrategyEs;
import cn.wolfcode.luowowo.search.domain.TravelEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelEsRepository extends ElasticsearchRepository<TravelEs, String>{
}
