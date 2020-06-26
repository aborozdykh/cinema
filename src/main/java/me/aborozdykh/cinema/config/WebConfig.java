package me.aborozdykh.cinema.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Andrii Borozdykh created on 07.06.2020
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"me.aborozdykh.cinema.controller"})
public class WebConfig {
}
