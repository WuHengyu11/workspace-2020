package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Permission;
import cn.wolfcode.domain.Role;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.service.IDepartmentService;
import cn.wolfcode.service.IPermissionService;
import cn.wolfcode.service.IRoleService;
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
    //关联service
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo){
        PageInfo<Role> pageInfo = roleService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "role/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id){
        roleService.delete(id);
        return "redirect:/role/list.do";
    }

    @RequestMapping("/input")
    public String input(Model model,Long id){
        //查询所有权限
        List<Permission> permissions = permissionService.listAll();
        model.addAttribute("permissions",permissions);
        if (id != null) {
            model.addAttribute("roles",roleService.get(id));
        }
        return "role/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role,Long[] ids){
        if (role.getId() != null) {
            roleService.update(role,ids);
        }else {
            roleService.save(role,ids);
        }
        return "redirect:/role/list.do";
    }
}
