package fajnelokalne.demo.repository;

import fajnelokalne.demo.entity.City;
import fajnelokalne.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);
    Page<User> findByUsernameLike(String name, Pageable pageable);
}