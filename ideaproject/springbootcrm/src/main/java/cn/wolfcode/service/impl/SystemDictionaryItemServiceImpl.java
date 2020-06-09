package cn.wolfcode.service.impl;

import cn.wolfcode.domain.SystemDictionaryItem;
import cn.wolfcode.mapper.SystemDictionaryItemMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.ISystemDictionaryItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemDictionaryItemServiceImpl implements ISystemDictionaryItemService {
    @Autowired
    private SystemDictionaryItemMapper systemDictionaryItemMapper;

    @Override
    @Transactional
    public void save(SystemDictionaryItem systemDictionaryItem) {
        systemDictionaryItemMapper.insert(systemDictionaryItem);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        systemDictionaryItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void update(SystemDictionaryItem systemDictionaryItem) {
        systemDictionaryItemMapper.updateByPrimaryKey(systemDictionaryItem);
    }

    @Override
    @Transactional
    public SystemDictionaryItem selectOne(Long id) {
        SystemDictionaryItem systemDictionaryItem = systemDictionaryItemMapper.selectByPrimaryKey(id);
        System.out.println(systemDictionaryItem);
        return systemDictionaryItem;
    }

    @Override
    @Transactional
    public List<SystemDictionaryItem> selectAll() {
        List<SystemDictionaryItem> depts = systemDictionaryItemMapper.selectAll();
        System.out.println(depts);
        return depts;
    }
    //调用Mapper接口实现类封装成PageResult返回
    @Override
    public PageInfo<SystemDictionaryItem> query(QueryObject qo) {
        //不需要手动执行count语句，由分页插件来执行
        //告诉分页插件，当前页以及每页显示的数据就可以了，分页插件会自动算出start只，放到list
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());//对下一句sql进行分页
        List<SystemDictionaryItem> systemDictionaryItems = systemDictionaryItemMapper.selectForList(qo);

        return new PageInfo<SystemDictionaryItem>(systemDictionaryItems);
    }

    @Override
    public List<SystemDictionaryItem> selectByDicsn(String sn) {
        return systemDictionaryItemMapper.selectByDicsn(sn);
    }

    @Override
    public List<SystemDictionaryItem> selectByParentId() {
        return systemDictionaryItemMapper.selectByParentId();
    }
}
