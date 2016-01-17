package com.action;

import com.delta.core.assembler.annotation.Detachable;
import com.delta.core.rover.RequestMethod;
import com.delta.core.rover.XForm;
import com.delta.core.rover.XFormConverter;
import com.delta.core.rover.XFormLoader;
import com.delta.core.rover.annotation.Controller;
import com.delta.core.rover.annotation.RequestMapping;
import com.delta.core.rover.except.XFormCastException;
import com.entity.User;
import com.form.UserForm;
import com.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller(namespace = "/user")
public class UserAction {
    UserService userService;

    @Detachable
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(patterns = "/login")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        return "/login.jsp";
    }

    @RequestMapping(patterns = "/regist")
    public String toRegister(HttpServletRequest request, HttpServletResponse response) {
        return "/register.jsp";
    }

    @RequestMapping(patterns = "/logout")
    public String doLogout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("user", null);
        return "redirect:/user/login";
    }

    @RequestMapping(patterns = "/doLogin", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, HttpServletResponse response) {
        User user;
        try {
            XForm userLoginForm = XFormLoader.newInstance(request, UserForm.class);
            user = XFormConverter.cast(userLoginForm, User.class);
        } catch (XFormCastException e) {
            return "redirect:/user/login?note=invalid form found.";
        }
        try {
            if ((user = userService.get(user)) == null) {
                return "redirect:/user/login?note=illegal identity.";
            }
            request.getSession().setAttribute("user", user);
            return "redirect:/friend/list";
        } catch (Exception e) {
            return "redirect:/user/login?note=" + e.getMessage();
        }
    }

    @RequestMapping(patterns = "/doRegist", method = RequestMethod.POST)
    public String doRegist(HttpServletRequest request, HttpServletResponse response) {
        User user = null;
        try {
            XForm userRegistForm = XFormLoader.newInstance(request, UserForm.class);
            user = XFormConverter.cast(userRegistForm, User.class);
        } catch (XFormCastException e) {
            return "out:" + e.getMessage();
        }
        try {
            userService.add(user);
            return "out:success";
        } catch (Exception e) {
            return "out:" + e.getMessage();
        }
    }
}
