package cn.wolfcode.elastic.config;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistryCenterConfig {
    @Bean(initMethod = "init")
    public CoordinatorRegistryCenter createRegistryCenter(@Value("${elastic-job.zookeeper-url}") String zookeeperUrl,@Value("${elastic-job.group-name}") String groupName) {
        //配置zk地址,调度任务的组名
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(zookeeperUrl, groupName);
        zookeeperConfiguration.setSessionTimeoutMilliseconds(100);
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
        return regCenter;
    }
}
