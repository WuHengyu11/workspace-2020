package cn.wolfcode.luowowo.search.repository;

import cn.wolfcode.luowowo.search.domain.DestinationEs;
import cn.wolfcode.luowowo.search.domain.StrategyEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategyEsRepository extends ElasticsearchRepository<StrategyEs, String>{
}
