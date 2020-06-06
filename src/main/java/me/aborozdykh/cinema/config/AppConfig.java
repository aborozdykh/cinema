package me.aborozdykh.cinema.config;

import java.util.Properties;
import javax.sql.DataSource;
import me.aborozdykh.cinema.models.CinemaHall;
import me.aborozdykh.cinema.models.Movie;
import me.aborozdykh.cinema.models.MovieSession;
import me.aborozdykh.cinema.models.Order;
import me.aborozdykh.cinema.models.ShoppingCart;
import me.aborozdykh.cinema.models.Ticket;
import me.aborozdykh.cinema.models.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 * @author Andrii Borozdykh created on 06.06.2020
 */
@Configuration
@ComponentScan("me.aborozdykh.cinema")
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
        var factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());

        var properties = new Properties();
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));

        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(CinemaHall.class);
        factoryBean.setAnnotatedClasses(User.class);
        factoryBean.setAnnotatedClasses(Movie.class);
        factoryBean.setAnnotatedClasses(MovieSession.class);
        factoryBean.setAnnotatedClasses(Order.class);
        factoryBean.setAnnotatedClasses(ShoppingCart.class);
        factoryBean.setAnnotatedClasses(Ticket.class);
        return factoryBean;
    }

}
