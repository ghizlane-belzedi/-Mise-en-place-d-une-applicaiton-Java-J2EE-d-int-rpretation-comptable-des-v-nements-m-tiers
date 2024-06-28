package ma.inetum.brique;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "codaEntityManagerFactory",
        transactionManagerRef = "codaTransactionManager",
        basePackages = { "ma.inetum.brique.repository.coda" })
public class CodaDBConfig {
	@Autowired
    private Environment env;
    @Bean(name="codaProps")
    @ConfigurationProperties(prefix =  "spring.coda.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }
    
    @Bean(name="codaDatasource")
    @ConfigurationProperties(prefix = "spring.coda.datasource")
    public DataSource datasource(@Qualifier("codaProps") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }

    
    @Bean(name="codaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder,
             @Qualifier("codaDatasource") DataSource dataSource){
    	LocalContainerEntityManagerFactoryBean entityManagerFactory = builder.dataSource(dataSource)
                .packages("ma.inetum.brique.model.coda")
                .persistenceUnit("Coda").build();
    	entityManagerFactory.setJpaProperties(this.properties());
        return entityManagerFactory;
    }

    @Bean(name = "codaTransactionManager")
    @ConfigurationProperties(prefix =  "spring.jpa.coda")
    public PlatformTransactionManager transactionManager(
            @Qualifier("codaEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
     	JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
    	return transactionManager;
    }

    private Properties properties() {
        Properties properties = new Properties();
//        properties.setProperty("hibernate.ddl-auto", this.env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("hibernate.globally_quoted_identifiers", "false");
        return properties;

    }
}
