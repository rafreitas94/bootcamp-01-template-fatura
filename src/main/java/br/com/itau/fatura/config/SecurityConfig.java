package br.com.itau.fatura.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .antMatchers(HttpMethod.POST, "/v1/cartoes/**").hasAuthority("SCOPE_fatura")
                .antMatchers(HttpMethod.GET, "/v1/cartoes/**").hasAuthority("SCOPE_fatura")
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
