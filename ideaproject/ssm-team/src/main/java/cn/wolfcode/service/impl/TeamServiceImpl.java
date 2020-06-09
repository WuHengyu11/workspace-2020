package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Team;
import cn.wolfcode.mapper.TeamMapper;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;
    @Override
    public void save(Team t) {
        teamMapper.insert(t);
    }

    @Override
    public void delete(Long id) {
        teamMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Team t) {
        teamMapper.updateByPrimaryKey(t);
    }

    @Override
    public Team get(Long id) {
        return teamMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Team> listAll() {
        return teamMapper.selectAll();
    }

    @Override
    public PageResult<Team> query(QueryObject qo) {
        int count = teamMapper.queryForCount(qo);
        if (count == 0){
            return new PageResult<>(Collections.emptyList(),qo.getCurrentPage(),qo.getPageSize(),count);
        }
        List<Team> employees = teamMapper.queryForList(qo);
        return new PageResult<>(employees,qo.getCurrentPage(),qo.getPageSize(),count);
    }
}
