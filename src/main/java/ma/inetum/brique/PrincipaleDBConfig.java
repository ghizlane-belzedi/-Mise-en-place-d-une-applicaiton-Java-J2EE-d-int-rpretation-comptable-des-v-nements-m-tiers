package ma.inetum.brique;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = { "ma.inetum.brique.repository.principale" })
public class PrincipaleDBConfig {
	@Primary
    @Bean(name="mainProps")
    @ConfigurationProperties("spring.brique.datasource")
    public DataSourceProperties dataSourceProperties() {
		DataSourceProperties dtSrcProperties = new DataSourceProperties();
		return dtSrcProperties;
    }

    @Primary
    @Bean(name="datasource")
    @ConfigurationProperties(prefix = "spring.brique.datasource")
    public DataSource datasource(@Qualifier("mainProps") DataSourceProperties properties){
    	DataSourceBuilder dtsrcBuiler = properties.initializeDataSourceBuilder();
    	DataSource dtSrc = dtsrcBuiler.build();
    	return dtSrc;
    }

    @Primary
    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder,
             @Qualifier("datasource") DataSource dataSource){
        return builder.dataSource(dataSource)
                .packages("ma.inetum.brique.model.principale")
                .persistenceUnit("Main").build();
    }

    @Primary
    @Bean(name = "transactionManager")
    @ConfigurationProperties("spring.jpa.brique")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
//    @Bean
//	 public PlatformTransactionManager platformTransactionManager(){ 
//	    return new JtaTransactionManager();
//	}
}
