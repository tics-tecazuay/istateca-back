package com.istateca.app.datasource;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryFenix",
        transactionManagerRef = "transactionManagerFenix",
        basePackages = {"com.istateca.app.fenix.fdaos"}// La posición de la capa Dao/Repository
)
public class FenixConfig {

    @Autowired
    @Qualifier("fenixDataSource")
    private DataSource fenixDataSource;

    @Autowired
    @Qualifier("vendorProperties")
    private Map<String, Object> vendorProperties;

    @Bean(name = "entityManagerFactoryFenix")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(fenixDataSource)
                .properties(vendorProperties)
                .packages("com.istateca.app.fenix.fmodels")
                .persistenceUnit("fenixPersistenceUnit")
                .build();
    }

    @Bean(name = "entityManagerFenix")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }

    @Bean(name = "transactionManagerFenix")
    PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
    }

}
