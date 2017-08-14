package com.docstorm.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
@SpringBootApplication
@Configuration
@EntityScan(basePackages={"com.docstorm.repository.entities"})
@ComponentScan(basePackages = { "com.docstorm" })
@EnableJpaRepositories("com.docstorm.jpa.repository")
@EnableTransactionManagement
@EnableSpringDataWebSupport
@EnableResourceServer
@EnableAutoConfiguration
 public class DocstormProjectApplication extends ResourceServerConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(DocstormProjectApplication.class, args);
	}
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
        .antMatchers("/**")
        .and().authorizeRequests()
        .antMatchers(HttpMethod.GET, "/**")
        .access("hasRole('USER')")
        .antMatchers("/**")
        .access("hasRole('ADMIN')").and();
    }
}
