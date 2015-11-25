package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

public class OAuth2ServerConfiguration {
	
	private static final String RESOURCE_ID = "restservice";
	
	
	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration
			extends ResourceServerConfigurerAdapter {
		
		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			resources.resourceId(RESOURCE_ID).stateless(true);
		}
		
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
			.httpBasic().and()
			.authorizeRequests().antMatchers("/*.html","/client","/*/regist","/*/register", "/oauth/authorize", "/oauth/confirm_access","/","/mail/*").permitAll()
			.and()
			.authorizeRequests()
					.anyRequest().authenticated();
		}
	}
	
	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
		
		@Autowired
		private AuthenticationManager authenticationManager;
		
		
		
		
		
		
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory()
				.withClient("restclient")
					.resourceIds(RESOURCE_ID)
					.authorizedGrantTypes("password","refresh_token")
					.authorities("ROLE_CLIENT")
					.scopes("read", "write")
					.secret("123456");
			
		}
		
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authenticationManager)
//			.tokenStore(tokenStore)
//			.reuseRefreshTokens(false)
			;
		}
		
		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer.tokenKeyAccess("permitAll()")			// /oauth/token_key
					   .checkTokenAccess("isAuthenticated()");	// /oauth/check_token
		}
		
	}
}
