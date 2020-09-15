package com.mbry.IronMan.Config;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class SimpleCORSFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// HttpServletResponse response = (HttpServletResponse) res;
		// HttpServletRequest request = (HttpServletRequest) req;
		// response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		// response.setHeader("Access-Control-Allow-Credentials", "true");
		// response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, PATCH, OPTIONS, DELETE");
		// response.setHeader("Access-Control-Max-Age", "3600");
		// // response.setHeader("Access-Control-Allow-Headers", "content-type, x-requested-with,authorization,X-XSRF-TOKEN");
		// // response.setHeader("Access-Control-Allow-Headers", "*");
        // response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        // System.out.println("[-] Access-control-Allow-Headers: " + request.getHeader("Access-Control-Request-Headers"));
		// if (!request.getMethod().equalsIgnoreCase("OPTIONS")) {
		// 	chain.doFilter(req, res);
		// } else {

        // }
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)req;
        HttpServletResponse httpServletResponse = (HttpServletResponse)res;

        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,PATCH,OPTIONS,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        if(!httpServletRequest.getMethod().equals("OPTIONS")){
            chain.doFilter(req, res);
        }else{

        }
        
	}

	public void init(FilterConfig filterConfig) {}

	public void destroy() {}

}
