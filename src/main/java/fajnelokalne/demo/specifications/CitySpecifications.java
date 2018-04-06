package fajnelokalne.demo.specifications;

import fajnelokalne.demo.entity.City;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CitySpecifications {

    public Specification<City> hasNameLike(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

}
