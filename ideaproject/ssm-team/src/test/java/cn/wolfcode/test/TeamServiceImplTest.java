package cn.wolfcode.test;

import cn.wolfcode.domain.Team;
import cn.wolfcode.service.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TeamServiceImplTest {
    @Autowired
    private TeamService teamService;
    @Test
    public void save() {
        Team team = new Team();
        team.setName("克利夫兰-骑士队");
        team.setAbbr("CLE");
        teamService.save(team);
    }

    @Test
    public void delete() {
        teamService.delete(2L);
    }

    @Test
    public void update() {
        Team team = new Team();
        team.setId(3L);
        team.setName("金州-勇士队");
        team.setAbbr("GSW");
        teamService.update(team);
    }

    @Test
    public void get() {
        Team team = teamService.get(1L);
        System.out.println(team);
    }

    @Test
    public void listAll() {
        List<Team> teams = teamService.listAll();
        for (Team team : teams) {
            System.out.println(team);
        }
    }
}