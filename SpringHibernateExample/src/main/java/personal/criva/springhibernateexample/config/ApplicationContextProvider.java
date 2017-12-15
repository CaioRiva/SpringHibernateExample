package personal.criva.springhibernateexample.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextProvider {

    private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringAppConfig.class);
    
    public static ApplicationContext getApplicationContext() {
     
	return applicationContext;
    }
    
}
