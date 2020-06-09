package cn.wolfcode.service.impl;

import cn.wolfcode.domain.SystemDictionary;
import cn.wolfcode.mapper.SystemDictionaryMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.ISystemDictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {
    @Autowired
    private SystemDictionaryMapper systemDictionaryMapper;

    @Override
    @Transactional
    public void save(SystemDictionary systemDictionary) {
        systemDictionaryMapper.insert(systemDictionary);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        systemDictionaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void update(SystemDictionary systemDictionary) {
        systemDictionaryMapper.updateByPrimaryKey(systemDictionary);
    }

    @Override
    @Transactional
    public SystemDictionary selectOne(Long id) {
        SystemDictionary systemDictionary = systemDictionaryMapper.selectByPrimaryKey(id);
        System.out.println(systemDictionary);
        return systemDictionary;
    }

    @Override
    @Transactional
    public List<SystemDictionary> selectAll() {
        List<SystemDictionary> depts = systemDictionaryMapper.selectAll();
        System.out.println(depts);
        return depts;
    }
    //调用Mapper接口实现类封装成PageResult返回
    @Override
    public PageInfo<SystemDictionary> query(QueryObject qo) {
        //不需要手动执行count语句，由分页插件来执行
        //告诉分页插件，当前页以及每页显示的数据就可以了，分页插件会自动算出start只，放到list
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());//对下一句sql进行分页
        List<SystemDictionary> systemDictionarys = systemDictionaryMapper.selectForList(qo);

        return new PageInfo<SystemDictionary>(systemDictionarys);
    }
}
