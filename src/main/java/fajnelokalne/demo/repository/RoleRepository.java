package fajnelokalne.demo.repository;

import fajnelokalne.demo.entity.Role;
import fajnelokalne.demo.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findOneByName(String name);
    Page<Role> findAllByNameLike(String name, Pageable pageable);
}