package rsa.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"rsa.encryption.realization", "rsa.generateKeys.realization", "rsa.logic",
"rsa.math.realization"}, basePackageClasses = rsa.DoThis.class, lazyInit = true)
public class ConfigSpring {

    private static ApplicationContext applicationContext;

    static {
        applicationContext = new AnnotationConfigApplicationContext(ConfigSpring.class);
    }

    @Bean
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }
}
