package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IPermissionService;
import cn.wolfcode.util.JsonResult;
import cn.wolfcode.util.RequiredPermission;
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
    @Autowired
    private IPermissionService permissionServiceImpl;

    @RequiredPermission(value = "权限列表",expression = "permission:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")QueryObject qo){
        PageInfo pageInfo = permissionServiceImpl.query(qo);
        model.addAttribute("result", pageInfo);

        return  "permission/list";
    }

    @RequiredPermission(value = "权限删除",expression = "permission:delete")
    @RequestMapping("/delete")
    public String delete(Long id){
        if(id !=null){
            permissionServiceImpl.delete(id);
        }
        return "redirect:/permission/list.do";
    }

    @RequestMapping("/reload")
    @ResponseBody
    public JsonResult reload(){
        try {
            permissionServiceImpl.reload();
            return new JsonResult();
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(false,"加载错误");
        }
    }


}
