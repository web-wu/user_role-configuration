package com.wxx.user_role.controller;

import com.wxx.user_role.entity.Role;
import com.wxx.user_role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleServlet {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/roleList")
    public ModelAndView getRoleList(ModelAndView modelAndView) {
        List<Role> roleList = roleService.getRoleList();
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping("/addRole")
    public String addRole ( Role role) {
        roleService.addRole(role);
        return "redirect:/role/roleList";
    }
}
