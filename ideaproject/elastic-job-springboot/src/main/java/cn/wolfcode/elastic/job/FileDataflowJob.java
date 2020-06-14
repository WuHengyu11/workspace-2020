package cn.wolfcode.elastic.job;

import cn.wolfcode.elastic.domain.FileCustom;
import cn.wolfcode.elastic.mapper.FileCustomMapper;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;
@Component
public class FileDataflowJob implements DataflowJob<FileCustom> {
    @Autowired
    private FileCustomMapper fileCustomMapper;
    @Override
    public List fetchData(ShardingContext shardingContext) {
        List<FileCustom> fileList = fileCustomMapper.fetchData(5);
        System.out.println(fileList);
        return fileList;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<FileCustom> data) {
        System.out.println("需要处理的数据个数" + data.size());
        for(FileCustom fileCustom:data){
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
