package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.httpBasic().and()
		.authorizeRequests().antMatchers("/*.html","/client","/*/regist","/*/register", "/oauth/authorize", "/oauth/confirm_access","/","/mail/*").permitAll()
		.and()
		.authorizeRequests()
				.anyRequest().authenticated();
	}
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
	    		.withUser("user").password("password").roles("USER").and()
	    		.withUser("admin").password("password").roles("ADMIN", "USER");
	}
	
	
//	public class CsrfHeaderFilter extends OncePerRequestFilter {
//		  @Override
//		  protected void doFilterInternal(HttpServletRequest request,
//		      HttpServletResponse response, FilterChain filterChain)
//		      throws ServletException, IOException {
//		    CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
//		        .getName());
//		    if (csrf != null) {
//		      Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
//		      String token = csrf.getToken();
//		      if (cookie==null || token!=null && !token.equals(cookie.getValue())) {
//		        cookie = new Cookie("XSRF-TOKEN", token);
//		        cookie.setPath("/");
//		        response.addCookie(cookie);
//		      }
//		    }
//		    filterChain.doFilter(request, response);
//		  }
//		}
	
}
