package com.sinodata.lottery.webexample.config;

import com.sinodata.lottery.webexample.GlobalControllerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/*@Configuration
public class MyWebMvcConfigurationSupport  extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GlobalControllerInterceptor()).addPathPatterns("/**").excludePathPatterns("/doc.html").excludePathPatterns("/actuator/**");
    }
}*/
