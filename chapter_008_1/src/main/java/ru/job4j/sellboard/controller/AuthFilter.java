package ru.job4j.sellboard.controller;

import ru.job4j.sellboard.controller.utils.AppUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Yury Matskevich
 */
public class AuthFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig)
			throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		if (req.getRequestURI().contains("/login")
				|| req.getRequestURI().contains("/createUser")
				|| req.getRequestURI().contains("/carsList")
				|| req.getRequestURI().contains("/doSearch")) {
			chain.doFilter(request, response);
		} else {
			if (session == null || AppUtils.getLoginedUser(session) == null) {
				resp.sendRedirect("/carsList");
				return;
			}
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}
}
