package org.example.filter;

import io.jsonwebtoken.Claims;
import org.example.model.User;
import org.example.service.JWTService;
import org.example.service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "securityFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class SecurityFilter implements Filter {
    private static final Set<String> ALLOWED_PATH = new HashSet<>(Arrays.asList("", "/login", "logout", "register"));
    private static final Set<String> IGNORED_PATH = new HashSet<>(Arrays.asList("/auth"));
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Check null or not before use. Initialize dependency first then inject.
        if (jwtService == null) {
            // Once injected, it will inject all dependencies required by various filter instance.
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletRequest.getServletContext());
        }
        logger.info("Start to do authorization");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        int statusCode = authorization(req);
        if(statusCode == HttpServletResponse.SC_ACCEPTED) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse)servletResponse).sendError(statusCode);
        }
    }

    private int authorization(HttpServletRequest req) {
        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        String uri = req.getRequestURI();
        if (IGNORED_PATH.contains(uri)) {
            return HttpServletResponse.SC_ACCEPTED;
        }

        try {
            String token = req.getHeader("Authorization").replaceAll("^(.*?) ", "");
            if (token == null || token.isEmpty())
                return statusCode;

            Claims claims = jwtService.decryptToken(token);
            logger.info("===== after parsing JWT token, claims.getId()={}", claims.getId());
            if (claims.getId() != null) {
                User u = userService.getUserById(Long.valueOf(claims.getId()));
                if (u != null) {
                    return HttpServletResponse.SC_ACCEPTED;
                }
            }
            String verb = req.getMethod();
            String allowedResources = "";
            switch (verb) {
                case "GET":
                    allowedResources = (String) claims.get("allowedResources");
                    break;
                case "POST":
                    allowedResources = (String) claims.get("allowedCreateResources");
                    break;
                case "PUT":
                    allowedResources = (String) claims.get("allowedUpdateResources");
                    break;
                case "DELETE":
                    allowedResources = (String) claims.get("allowedDeleteResources");
            }

            for (String s : allowedResources.split(",")) {
                if (uri.trim().toLowerCase().startsWith(s.trim().toLowerCase())) {
                    statusCode = HttpServletResponse.SC_ACCEPTED;
                    break;
                }
            }

        } catch (Exception e) {
            logger.info("Cannot get token");
        }
        return statusCode;
    }
}
