package me.aborozdykh.cinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author Andrii Borozdykh created on 07.06.2020
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"me.aborozdykh.cinema.controller"})
public class WebConfig {

    @Bean
    public InternalResourceViewResolver getResolver() {
        var resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
