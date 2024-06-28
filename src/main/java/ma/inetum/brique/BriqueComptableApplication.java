package ma.inetum.brique;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.http.CacheControl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableScheduling
//@EnableJpaRepositories(enableDefaultTransactions = false)
public class BriqueComptableApplication implements WebMvcConfigurer{
//public class BriqueComptableApplication extends SpringBootServletInitializer {

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Register resource handler for images
        registry.addResourceHandler("/assets1/**").addResourceLocations("/WEB-INF/assets1/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
    }
	
	public static void main(String[] args) {
		SpringApplication.run(BriqueComptableApplication.class, args);
	}
	
//	@Bean 
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
	
	@Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
	@Bean
	public LocaleResolver localeResolver() {
	   SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
	   sessionLocaleResolver.setDefaultLocale(Locale.FRANCE);
	   return sessionLocaleResolver;
	}
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	   LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	   localeChangeInterceptor.setParamName("lang");
	   return localeChangeInterceptor;
	}
	@Bean(name = "chainedTransactionManager")
	public ChainedTransactionManager getChainedTransactionManager(@Qualifier ("codaTransactionManager") JpaTransactionManager tm1, @Qualifier ("transactionManager") JpaTransactionManager tm2){
	    return new ChainedTransactionManager(tm1, tm2);
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	   registry.addInterceptor(localeChangeInterceptor());
	}
}
