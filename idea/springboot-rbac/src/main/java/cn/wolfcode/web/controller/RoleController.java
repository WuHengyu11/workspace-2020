package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.domain.Role;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IPermissionService;
import cn.wolfcode.service.IRoleService;
import cn.wolfcode.util.RequiredPermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleServiceImpl;

    @Autowired
    private IPermissionService permissionServiceImpl;

    @RequiredPermission(value = "角色列表",expression = "role:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo){
        PageInfo pageInfo = roleServiceImpl.query(qo);
        model.addAttribute("result", pageInfo);
        return "role/list";
    }

    @RequiredPermission(value = "角色编辑",expression = "role:input")
    @RequestMapping("/input")
    public String input(Model model,Long id){
        //查询所有权限
        List<Permission> permissions = permissionServiceImpl.selectAll();
        model.addAttribute("permissions",permissions);

        if(id != null){
            Role role = roleServiceImpl.getById(id);
            model.addAttribute("role",role);
        }
        return "role/input";
    }
    @RequiredPermission(value = "角色存入和更新",expression = "role:saveOrUpdate")
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role, Long[] ids){

        if(role.getId() != null){
            roleServiceImpl.update(role,ids);
        }else{
            roleServiceImpl.save(role,ids);

        }
        return "redirect:/role/list.do";
    }

}
