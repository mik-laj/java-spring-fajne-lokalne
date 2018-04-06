package fajnelokalne.demo.initializers;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Profile("!production")
public class RepositoryInitializerRunner implements InitializingBean {

    @Autowired
    UserRepositoriesInitializer userRepositoriesInitializer;

    @Autowired
    DomainRepositoriesInitializer domainRepositoriesInitializer;

    @Autowired
    ReviewRepositoriesInitializer reviewRepositoriesInitializer;

    @Override
    public void afterPropertiesSet() throws Exception {
        userRepositoriesInitializer.run();
        domainRepositoriesInitializer.run();
        reviewRepositoriesInitializer.run();
    }
}
