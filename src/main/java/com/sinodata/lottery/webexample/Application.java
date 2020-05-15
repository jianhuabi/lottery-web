
package com.sinodata.lottery.webexample;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
         SpringApplication.run(Application.class, args);
    }

    // 监控 jvm
    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName){
        return registry -> registry.config().commonTags("application",applicationName);
    }



}
