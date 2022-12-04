package com.reagente.manager.manager.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/adm/**").permitAll()
                .antMatchers("/api/**").hasRole("ADM")
                .antMatchers("/api/aluno/**").hasRole("PROF")
                .antMatchers("/api/documentos/**").hasRole("PROF")
                .antMatchers("/api/categoria/{id}").hasRole("PROF")
                .antMatchers("/api/laboratorio/{id}").hasRole("PROF")
                .antMatchers("/api/laboratorio").hasRole("PROF")
                .antMatchers("/api/professor/{id}").hasRole("PROF")
                .antMatchers("/api/projeto").hasAnyRole("PROF", "USER")
                .antMatchers("/api/projeto/{id}").hasRole("USER")
                .antMatchers("/api/projeto/**").hasRole("PROF")
                .antMatchers("/api/reagente/{id}").hasRole("PROF")
                .antMatchers("/api/reagente").hasRole("PROF")
                .anyRequest().denyAll();
    }
}
