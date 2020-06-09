package cn.wolfcode.web.controller;

import cn.wolfcode.domain.SystemDictionaryItem;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.qo.SystemDictionaryItemQueryObject;
import cn.wolfcode.service.ISystemDictionaryItemService;
import cn.wolfcode.service.ISystemDictionaryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/systemDictionaryItem")
public class SystemDictionaryItemController {
    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemServiceImpl;

    @Autowired
    private ISystemDictionaryService systemDictionaryService;

//    @RequiredPermission(value = "部门列表",expression = "systemDictionaryItem:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")SystemDictionaryItemQueryObject qo){
        PageInfo<SystemDictionaryItem> pageResult = systemDictionaryItemServiceImpl.query(qo);
        model.addAttribute("pageInfo",pageResult);
        //查询所有的字典目录
        model.addAttribute("systemDictionarys",systemDictionaryService.selectAll());
        return  "systemDictionaryItem/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            systemDictionaryItemServiceImpl.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "操作失败");
        }

    }

    /*@RequiredPermission(value = "部门输入",expression = "systemDictionaryItem:input")
    @RequestMapping("/input")
    public String input(Long id,Model model){
        if(id != null){
            SystemDictionaryItem systemDictionaryItem = systemDictionaryItemServiceImpl.selectOne(id);
            model.addAttribute("systemDictionaryItem",systemDictionaryItem);
        }
        return "systemDictionaryItem/input";
    }*/
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(SystemDictionaryItem systemDictionaryItem) {
        try {
            if (systemDictionaryItem.getId() != null) {
                systemDictionaryItemServiceImpl.update(systemDictionaryItem);
            } else {
                systemDictionaryItemServiceImpl.save(systemDictionaryItem);
            }
            return  new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return  new JsonResult(false,"操作失败");

        }

    }

}
