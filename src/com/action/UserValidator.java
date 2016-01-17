package com.action;

import com.delta.core.rover.ActionInterceptor;
import com.delta.core.rover.except.AccessDenyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class UserValidator implements ActionInterceptor{
    @Override
    public String intercept(Method method, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AccessDenyException {
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            return "redirect:/user/login?note=Your identity expired.";
        }
        return null;
    }
}
