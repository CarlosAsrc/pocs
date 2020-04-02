package com.ages.incuitec.backend.configuration;

import com.ages.incuitec.backend.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<AuthFilter> loggingFilter(){
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/v1/user/*");

        return registrationBean;
    }
}
