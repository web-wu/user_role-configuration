package com.wxx.user_role.controller;

import com.wxx.user_role.entity.Role;
import com.wxx.user_role.entity.User;
import com.wxx.user_role.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserServlet {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping("/getuserList")
    public ModelAndView getUserList (ModelAndView modelAndView) {
        List<User> userList = userService.getUserList();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/userUi")
    public ModelAndView activeRole(ModelAndView modelAndView) {
        List<Role> roleList = userService.activeRole();
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }

    @RequestMapping("/addUser")
    public String addUser(User user, Long[] roleIds) {
        userService.addUSer(user, roleIds);
        return "redirect:/user/getuserList";
    }

    @RequestMapping("/login")
    public String login(User user, HttpSession session) throws Exception {
        User _user = userService.login(user);
        if (_user != null) {
            session.setAttribute("user", _user);
            return "redirect:/index.jsp";
        }
        return "redirect:/login.jsp";
    }
}
