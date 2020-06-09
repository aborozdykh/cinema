package me.aborozdykh.cinema.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 * @author Andrii Borozdykh
 */
@Configuration
@ComponentScan(basePackages = {
        "me.aborozdykh.cinema.service",
        "me.aborozdykh.cinema.dao",
        "me.aborozdykh.cinema.security",
        "me.aborozdykh.cinema.util"
})
@PropertySource("classpath:db.properties")
public class AppConfig {
    private final Environment environment;

    @Autowired
    public AppConfig(Environment env) {
        this.environment = env;
    }

    @Bean
    public DataSource getDataSource() {
        var dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.user"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        var sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        var properties = new Properties();
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        sessionFactory.setHibernateProperties(properties);
        sessionFactory.setPackagesToScan("me.aborozdykh.cinema.models");
        return sessionFactory;
    }

}
