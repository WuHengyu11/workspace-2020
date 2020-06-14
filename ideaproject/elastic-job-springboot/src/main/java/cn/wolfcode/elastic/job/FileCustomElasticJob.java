package cn.wolfcode.elastic.job;

import cn.wolfcode.elastic.domain.FileCustom;
import cn.wolfcode.elastic.mapper.FileCustomMapper;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class FileCustomElasticJob implements SimpleJob {
    @Autowired
    private FileCustomMapper fileCustomMapper;
    @Override
    public void execute(ShardingContext shardingContext) {
        doWork(shardingContext.getShardingParameter());
    }
    private void doWork(String fileType){
        List<FileCustom> fileList = fileCustomMapper.selecByType(fileType);
        System.out.println("类型为:"+fileType+",文件，需要备份个数:"+fileList.size());
        for(FileCustom fileCustom:fileList){
            backUpFile(fileCustom);
        }
    }
    private void backUpFile(FileCustom fileCustom){
        try {
            //模拟备份动作
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行文件备份====>"+fileCustom);
        fileCustomMapper.changeState(fileCustom.getId(),1);
    }
}