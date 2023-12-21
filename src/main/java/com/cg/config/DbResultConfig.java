package com.cg.config;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.dialect.SQLServer2012Dialect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "resultEntityManagerFactory",
        transactionManagerRef = "resultTransactionManager",
        basePackages = {"com.cg.result.repository"})
public class DbResultConfig {

    @Bean(name = "resultDataSourceProperties")
    @ConfigurationProperties("spring.datasource.second")
    public DataSourceProperties resultDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean(name = "resultDataSource")
    @ConfigurationProperties("spring.datasource.second.configuration")
    public DataSource resultDataSource(@Qualifier("resultDataSourceProperties")
                                       DataSourceProperties resultDataSourceProperties) {
        return resultDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }


    @Bean(name = "resultEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean resultEntityManagerFactory(
            EntityManagerFactoryBuilder resultEntityManagerFactoryBuilder, @Qualifier("resultDataSource") DataSource resultDataSource) {
        Map<String, Object> props = new HashMap<>();
//        props.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
//        props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        props.put("hibernate.dialect", SQLServer2012Dialect.class.getName());
        props.put("hibernate.hbm2ddl.auto", "validate");

//        Map<String, String> resultJpaProperties = new HashMap<>();
//        resultJpaProperties.put("jpa.properties.hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
//        resultJpaProperties.put("hibernate.hbm2ddl.auto", "update");

        return resultEntityManagerFactoryBuilder
                .dataSource(resultDataSource)
                .packages("com.cg.result.entity")
                .persistenceUnit("resultDataSource")
                .properties(props)
                .build();
    }


    @Bean(name = "resultTransactionManager")
    public PlatformTransactionManager resultTransactionManager(
            @Qualifier("resultEntityManagerFactory") EntityManagerFactory resultEntityManagerFactory) {

        return new JpaTransactionManager(resultEntityManagerFactory);
    }
}