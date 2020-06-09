package cn.wolfcode.web.controller;

import cn.wolfcode.domain.SystemDictionary;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.ISystemDictionaryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/systemDictionary")
public class SystemDictionaryController {
    @Autowired
    private ISystemDictionaryService systemDictionaryServiceImpl;

//    @RequiredPermission(value = "部门列表",expression = "systemDictionary:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")QueryObject qo){
        PageInfo<SystemDictionary> pageResult = systemDictionaryServiceImpl.query(qo);
        model.addAttribute("pageInfo",pageResult);
        return  "systemDictionary/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            systemDictionaryServiceImpl.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "操作失败");
        }

    }

    /*@RequiredPermission(value = "部门输入",expression = "systemDictionary:input")
    @RequestMapping("/input")
    public String input(Long id,Model model){
        if(id != null){
            SystemDictionary systemDictionary = systemDictionaryServiceImpl.selectOne(id);
            model.addAttribute("systemDictionary",systemDictionary);
        }
        return "systemDictionary/input";
    }*/
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(SystemDictionary systemDictionary) {
        try {
            if (systemDictionary.getId() != null) {
                systemDictionaryServiceImpl.update(systemDictionary);
            } else {
                systemDictionaryServiceImpl.save(systemDictionary);
            }
            return  new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return  new JsonResult(false,"操作失败");

        }

    }

}
