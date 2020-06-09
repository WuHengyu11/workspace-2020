package cn.wolfcode.luowowo.search.repository;

import cn.wolfcode.luowowo.search.domain.TravelEs;
import cn.wolfcode.luowowo.search.domain.UserInfoEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoEsRepository extends ElasticsearchRepository<UserInfoEs, String>{
}
