package br.com.unimedceara.core.config;

import javax.servlet.Filter;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.keycloak.adapters.springsecurity.filter.KeycloakPreAuthActionsFilter;
import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class KeycloakSpringSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(keycloakAuthenticationProvider());
	}
	
	@Bean
	public KeycloakConfigResolver keycloakConfigResolver() {
	    return new PathBasedKeycloakConfigResolver();
	}

	@Bean
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new NullAuthenticatedSessionStrategy();
	}

	@Bean
	public FilterRegistrationBean<Filter> keycloakAuthenticationProcessingFilterRegistrationBean(KeycloakAuthenticationProcessingFilter filter) {
		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<Filter>(filter);
		registrationBean.setEnabled(false);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<Filter> keycloakPreAuthActionsFilterRegistrationBean(KeycloakPreAuthActionsFilter filter) {
		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<Filter>(filter);
		registrationBean.setEnabled(false);
		return registrationBean;
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/teste**",
        						   "/v2/api-docs",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html",
                                   "/webjars/**",
		                           "/pacientes/**");
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.sessionAuthenticationStrategy(sessionAuthenticationStrategy())
			.and()
			.addFilterBefore(keycloakPreAuthActionsFilter(), LogoutFilter.class)
			.addFilterBefore(keycloakAuthenticationProcessingFilter(), X509AuthenticationFilter.class)
			.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint())
			.and()
			.authorizeRequests()
			.antMatchers("/triagil/**").permitAll()
			.antMatchers("/pacientes/**").permitAll()
			.antMatchers("/admin-tools/**").permitAll()
			.antMatchers("/v1/alt-auth/**").permitAll()
			.antMatchers("/consultas/**").permitAll()
			.anyRequest().authenticated();
		http.cors();
	}	
	
    @Bean
    @Override
    @ConditionalOnMissingBean(HttpSessionManager.class)
    protected HttpSessionManager httpSessionManager() {
        return new HttpSessionManager();
    }
}
