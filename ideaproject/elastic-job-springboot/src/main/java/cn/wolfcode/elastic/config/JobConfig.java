package cn.wolfcode.elastic.config;

import cn.wolfcode.elastic.domain.FileCustom;
import cn.wolfcode.elastic.job.FileCustomElasticJob;
import cn.wolfcode.elastic.job.FileDataflowJob;
import cn.wolfcode.elastic.job.MyElasticJob;
import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@Configuration
public class JobConfig {
    @Autowired
    private CoordinatorRegistryCenter registryCenter;
    @Autowired
    private DataSource dataSource;
    /**
    @Bean(initMethod = "init")
    public SpringJobScheduler initMyElasticJob(MyElasticJob myElasticJob){
        //new SpringJobScheduler(任务类对象,注册中心对象,);
        return new SpringJobScheduler(myElasticJob,registryCenter,createJobConfiguration(MyElasticJob.class,"0/6 * * * * ?",1,null));
    }*/
    /*
    @Bean(initMethod = "init")
    public SpringJobScheduler initFileCustomJob(FileCustomElasticJob fileCustomElasticJob){
        //new SpringJobScheduler(任务类对象,注册中心对象,);
        return new SpringJobScheduler(fileCustomElasticJob,registryCenter,createJobConfiguration(FileCustomElasticJob.class,"0/6 * * * * ?",4,"0=text,1=image,2=radio,3=vedio"));
    }
    */
    @Bean(initMethod = "init")
    public SpringJobScheduler initFileCustomJob(FileDataflowJob fileDataflowJob){
        //增加任务事件追踪配置
        JobEventConfiguration jobEventConfiguration = new JobEventRdbConfiguration(dataSource);
        SpringJobScheduler springJobScheduler = new SpringJobScheduler(
                fileDataflowJob,
                registryCenter,
                createJobConfiguration(FileCustomElasticJob.class,"0 0/1 * * * ?",4,"0=text,1=image,2=radio,3=vedio",true),
                jobEventConfiguration);
        return springJobScheduler;
        //new SpringJobScheduler(任务类对象,注册中心对象,);
        //return new SpringJobScheduler(fileDataflowJob,registryCenter,createJobConfiguration(FileDataflowJob.class,"0/6 * * * * ?",4,"0=text,1=image,2=radio,3=vedio",true));
    }
    /**
     * 通用的方法
     * @return
     */
    private static LiteJobConfiguration createJobConfiguration(final Class<? extends ElasticJob> jobClass,//人物类字节码
                                                               final String cron,                        //任务执行的表达式
                                                               final int shardingTotalCount,             //分片总数
                                                               final String shardingItemParameters,      //分片参数
                                                               boolean isdataflowType) {
        // 定义作业核心配置
        JobCoreConfiguration.Builder jobCoreConfigurationBuilder = JobCoreConfiguration.newBuilder(jobClass.getSimpleName(), cron, shardingTotalCount);
        //设置分片参数
        if (!StringUtils.isEmpty(shardingItemParameters)){
            jobCoreConfigurationBuilder.shardingItemParameters(shardingItemParameters);
        }
        JobCoreConfiguration build = jobCoreConfigurationBuilder.build();
        JobTypeConfiguration typeConfiguration = null;
        if (isdataflowType){
            //任务类属于DataFlow类型
            typeConfiguration = new DataflowJobConfiguration(build,jobClass.getCanonicalName(),true);
        }else {
            // 定义SIMPLE类型配置
            typeConfiguration = new SimpleJobConfiguration(build, MyElasticJob.class.getCanonicalName());
        }
        // 定义Lite作业根配置
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(typeConfiguration).overwrite(true).build();
        return simpleJobRootConfig;
    }
}
