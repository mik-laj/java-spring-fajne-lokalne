package fajnelokalne.demo.specifications;

import fajnelokalne.demo.entity.City;
import fajnelokalne.demo.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserSpecifications {

    public Specification<User> hasUsernameLike(String name) {
        return (root, query, builder) -> builder.like(root.get("username"), "%" + name + "%");
    }

}
