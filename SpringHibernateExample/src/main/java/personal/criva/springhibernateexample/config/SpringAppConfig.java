package personal.criva.springhibernateexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("personal.criva.springhibernateexample.model.dao"),
	@ComponentScan("personal.criva.springhibernateexample.service") })
public class SpringAppConfig {

    @Bean
    public LocalEntityManagerFactoryBean getEntityManagerFactoryBean() {

	LocalEntityManagerFactoryBean entityManagerFactoryBean = new LocalEntityManagerFactoryBean();
	entityManagerFactoryBean.setPersistenceUnitName("LOCAL_PERSISTENCE");

	return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager  getJpaTransactionManager() {

	JpaTransactionManager transactionManager = new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(getEntityManagerFactoryBean().getObject());

	return transactionManager;
    }
}
