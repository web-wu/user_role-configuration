package com.wxx.user_role.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionHander implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        if (e instanceof EmptyResultDataAccessException) {
            modelAndView.setViewName("500");
        }
        return modelAndView;
    }
}
