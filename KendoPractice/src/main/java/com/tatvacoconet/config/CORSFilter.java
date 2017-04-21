package com.tatvacoconet.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author TatvaSoft
 *
 */
public class CORSFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Access-Control-Allow-Origin", "*");
		//response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		//response.setHeader("Access-Control-Allow-Headers", "X-requested-with, Content-Type");
		response.setHeader("Access-Control-Allow-Headers", "*");
		response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
		
		if (request.getMethod()!="OPTIONS") {
			chain.doFilter(req, res);
		} else {}
	}

	public void init(FilterConfig filterConfig) {}

	public void destroy() {}

}