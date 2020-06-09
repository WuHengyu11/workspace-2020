package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import cn.wolfcode.service.IPermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    //关联service
    @Autowired
    private IPermissionService permissionService;
    

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo){
        PageInfo<Permission> pageInfo = permissionService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "permission/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id){
        permissionService.delete(id);
        return "redirect:/permission/list.do";
    }
    @RequestMapping("/reload")
    @ResponseBody
    public JsonResult reload(){
        try {
            permissionService.reload();
            return new JsonResult();
        }catch (Exception e){
            e.printStackTrace();
            return  new JsonResult(false,"加载失败,请联系管理员");
        }

    }
}
