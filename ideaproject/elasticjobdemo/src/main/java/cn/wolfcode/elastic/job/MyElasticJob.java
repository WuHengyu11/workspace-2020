package cn.wolfcode.elastic.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.util.Date;

public class MyElasticJob implements SimpleJob {
    public void execute(ShardingContext shardingContext) {
        //定时业务逻辑
        System.out.println("执行定时任务" + new Date());
    }
}
