package fajnelokalne.demo.service;

import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.entity.Role;
import fajnelokalne.demo.repository.RoleRepository;
import io.springlets.format.EntityResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleManager implements EntityResolver<Role, String> {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findOne(String name) {
        return roleRepository.findOneByName(name);
    }

    @Override
    public Class<Role> getEntityType() {
        return Role.class;
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    public Page<Role> search(String name, Pageable pageable) {
        String keyword = "%" + name + "%";
        return roleRepository.findAllByNameLike(keyword, pageable);
    }
}
