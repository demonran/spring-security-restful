//package com.example;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class CorsFilter implements Filter{
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//			throws IOException, ServletException {
//			
//		HttpServletResponse response = (HttpServletResponse)res;
//		
//		HttpServletRequest request = (HttpServletRequest)req;
//		
//		 	response.addHeader("Access-Control-Allow-Origin", "*");
//	        response.addHeader("Access-Control-Max-Age", "120"); // in seconds
//	        response.addHeader("Access-Control-Allow-Credentials", "true");
//	        response.addHeader("Access-Control-Allow-Methods",
//	                        "HEAD, GET, OPTIONS, POST, PUT, DELETE");
//	        response.addHeader("Access-Control-Allow-Headers",
//	                        "origin, content-type, accept, x-requested-with");
//	        if (request.getMethod()!="OPTIONS") {
//	            chain.doFilter(req, res);
//	          }
//	}
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
