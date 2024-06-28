package ma.inetum.brique;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "metierEntityManagerFactory",
        transactionManagerRef = "metierTransactionManager",
        basePackages = { "ma.inetum.brique.repository.metier" })
public class MetierDBConfig {
	
    @Bean(name="metierProps")
    @ConfigurationProperties("spring.metier.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }
    
    @Bean(name="metierDatasource")
    @ConfigurationProperties(prefix = "spring.metier.datasource")
    public DataSource datasource(@Qualifier("metierProps") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }

    
    @Bean(name="metierEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder,
             @Qualifier("metierDatasource") DataSource dataSource){
        return builder.dataSource(dataSource)
                .packages("ma.inetum.brique.model.metier")
                .persistenceUnit("Metier").build();
    }

    @Bean(name = "metierTransactionManager")
    @ConfigurationProperties("spring.jpa.metier")
    public PlatformTransactionManager transactionManager(
            @Qualifier("metierEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
