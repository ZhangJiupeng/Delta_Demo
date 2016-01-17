package com.action;

import com.delta.core.rover.ActionInterceptor;
import com.delta.core.rover.except.AccessDenyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;

public class XSSDispatcher implements ActionInterceptor {
    @Override
    public String intercept(Method method, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AccessDenyException {
        String queryString = httpServletRequest.getQueryString();
        try {
            queryString = queryString == null ? null : URLDecoder.decode(queryString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        if (queryString != null && (queryString.contains("<") || queryString.contains("'") || queryString.contains("\""))) {
            return "redirect:/user/login?note=XSS detected.";
        }
        return null;
    }
}
