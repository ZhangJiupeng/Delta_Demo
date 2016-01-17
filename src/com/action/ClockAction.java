package com.action;

import com.delta.core.rover.annotation.Controller;
import com.delta.core.rover.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ClockAction {
    @RequestMapping(patterns = "/timer")
    public String showTimer(HttpServletRequest request, HttpServletResponse response) {
        return "/timer.jsp";
    }
}
