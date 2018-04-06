package fajnelokalne.demo.config;

import fajnelokalne.demo.domain.MyUserDetails;
import fajnelokalne.demo.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<User> auditorProvider() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()) {
                return null;
            }

            Object principal = authentication.getPrincipal();
            if (!(principal instanceof MyUserDetails)) {
                return null;
            }
            return ((MyUserDetails) principal).getUser();
        };
    }
}
