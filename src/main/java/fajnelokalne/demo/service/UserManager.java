package fajnelokalne.demo.service;

import fajnelokalne.demo.entity.User;
import fajnelokalne.demo.filters.UserFilter;
import fajnelokalne.demo.formdata.CreateUserFormData;
import fajnelokalne.demo.formdata.EditUserFormData;
import fajnelokalne.demo.formdata.RegistrationFormData;
import fajnelokalne.demo.repository.RoleRepository;
import fajnelokalne.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserFilter userFilter;

    public Page<User> findByUsernameLike(String name, Pageable pageable) {
        String keyword = "%" + name + "%";
        return userRepository.findByUsernameLike(name, pageable);
    }

    public Page<User> findAll(String username, Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Page<User> findAll(UserFilter.FilterParameter filter, Pageable pageable) {
        return userRepository.findAll(userFilter.apply(filter), pageable);
    }

    public User save(RegistrationFormData formData) {
        User user = new User();
        user.setFirstName(formData.getFirstName());
        user.setLastName(formData.getLastName());
        user.setUsername(formData.getUsername());
        user.setPassword(passwordEncoder.encode(formData.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));

        userRepository.save(user);
        return user;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User create(CreateUserFormData formData) {
        User u = new User();
        copyProductFormData(u, formData);
        u.setPassword(passwordEncoder.encode(formData.getPassword()));
        userRepository.save(u);
        return u;
    }

    private void copyProductFormData(User u, EditUserFormData formData) {
        u.setFirstName(formData.getFirstName());
        u.setLastName(formData.getLastName());
        u.setUsername(formData.getUsername());
        u.getRoles().clear();
        if(formData.getRoles() != null) {
            formData.getRoles().stream()
                    .map(roleRepository::findOneByName)
                    .forEach(u::addRole);
        }
    }

    public User update(User user, EditUserFormData formData) {
        copyProductFormData(user, formData);
        userRepository.save(user);
        return user;
    }
}
