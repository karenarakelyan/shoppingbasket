package com.karen.shoppingbasket.security.core;

import com.karen.shoppingbasket.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
@ComponentScan(value = "com.karen.shoppingbasket.services")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenService tokenService;

    @Autowired
    public WebSecurityConfig(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .addFilterBefore(createCustomFilter(), AnonymousAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new TokenAuthenticationProvider(tokenService);
    }

    private AbstractAuthenticationProcessingFilter createCustomFilter() throws Exception {
        final TokenAuthenticationFilter filter = new TokenAuthenticationFilter(
                new NegatedRequestMatcher(
                        new AndRequestMatcher(
                                new AntPathRequestMatcher("/authentication/login"),
                                new AntPathRequestMatcher("/authentication/login-token")
                        )
                )
        );
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
}