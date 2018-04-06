package fajnelokalne.demo.initializers;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import fajnelokalne.demo.entity.*;
import fajnelokalne.demo.formdata.RegistrationFormData;
import fajnelokalne.demo.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
@Profile("!production")
public class UserRepositoriesInitializer {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void run() throws Exception {

        List<User> users = userRepository.findAll();

        if (users.size() == 0) {
            Role userRole = new Role("ROLE_USER");
            roleRepository.save(userRole);

            Role adminRole = new Role("ROLE_ADMIN");
            roleRepository.save(adminRole);

            User user = new User();
            user.setFirstName("Jan");
            user.setLastName("Marchew");
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("pass"));
            user.setRoles(new HashSet<>(Arrays.asList(userRole)));
            userRepository.save(user);

            User admin = new User();
            admin.setFirstName("Izydor");
            admin.setLastName("Kowalczyk");
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("pass"));
            admin.setRoles(new HashSet<>(Arrays.asList(userRole, adminRole)));
            userRepository.save(admin);

        }



    }
}
