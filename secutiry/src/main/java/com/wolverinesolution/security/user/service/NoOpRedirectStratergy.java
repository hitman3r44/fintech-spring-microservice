package com.wolverinesolution.security.user.service;

import org.springframework.security.web.RedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoOpRedirectStratergy implements RedirectStrategy {
    @Override
    public void sendRedirect(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s) throws IOException {
        //No Op
    }
}
