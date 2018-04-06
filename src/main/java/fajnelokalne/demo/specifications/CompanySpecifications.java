package fajnelokalne.demo.specifications;

import fajnelokalne.demo.entity.City;
import fajnelokalne.demo.entity.Company;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CompanySpecifications {

    public Specification<Company> hasNameLike(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

    public Specification<Company> hasCityId(Long city) {
        return (root, query, builder) -> builder.equal(root.get("city").get("id"), city);
    }
}
