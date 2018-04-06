package fajnelokalne.demo.config;


import io.springlets.format.config.SpringletsEntityFormatWebConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class MvcConfig extends SpringletsEntityFormatWebConfiguration {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/assets/",
    };

    public MvcConfig(MessageSource messageSource, ApplicationContext applicationContext) {
        super(messageSource, applicationContext);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations(
                CLASSPATH_RESOURCE_LOCATIONS);
    }
}
