package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Team;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.TeamQueryObject;
import cn.wolfcode.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")TeamQueryObject tqo){
        PageResult<Team> pageResult = teamService.query(tqo);
        model.addAttribute("pageResult",pageResult);
        return "team/list";
    }

    @RequestMapping("/input")
    public String input(Model model,Long id){
        if (id != null) {
            model.addAttribute("team",teamService.get(id));
        }
        return "team/input";
    }
    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Team team){
        if (team.getId() != null) {
            teamService.update(team);
        }else {
            teamService.save(team);
        }
        return "redirect:/team/list.do";
    }

    @RequestMapping("/delete")
    public String delete(Long id){
        if (id != null) {
            teamService.delete(id);
        }
        return "redirect:/team/list.do";
    }
}
