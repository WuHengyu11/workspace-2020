package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Hero;
import cn.wolfcode.qo.HeroQueryObject;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.service.IHeroService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hero")
public class HeroController {
    @Autowired
    private IHeroService heroService;

    @RequestMapping("/list")
    public String list(Model model,@ModelAttribute("qo") HeroQueryObject qo){
        PageResult<Hero> pageResult = heroService.query(qo);
        model.addAttribute("pageResult",pageResult);
        return "hero/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id){
        heroService.delete(id);
        return "redirect:/hero/list.do";
    }

    @RequestMapping("/input")
    public String input(Long id, Model model){
        if (id != null) {
            model.addAttribute("hero",heroService.get(id));
        }
        return "hero/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Hero hero){
        if (hero.getId() != null) {
            heroService.update(hero);
        }else {
            heroService.save(hero);
        }
        return "redirect:/hero/list.do";
    }
}
