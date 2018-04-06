package fajnelokalne.demo.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.function.BiFunction;
import java.util.function.Function;

@Configuration
public class ThemeFunc {

    @Bean
    public Function<String, String> currentUrlWithoutParam() {
        return param -> ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replaceQueryParam(param)
                .toUriString();
    }

    @Bean
    public BiFunction<String, String, String> currentUrlWithoutParams2() {
        return (param, param2) -> ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replaceQueryParam(param)
                .replaceQueryParam(param2)
                .toUriString();
    }

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver, SpringSecurityDialect sec, LayoutDialect layoutDialect) {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.addDialect(sec); // Enable use of "sec"
        templateEngine.addDialect(layoutDialect);
        return templateEngine;
    }
}
