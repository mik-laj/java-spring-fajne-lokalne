package fajnelokalne.demo.specifications;

import fajnelokalne.demo.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductSpecifications {

    public Specification<Product> hasNameLike(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

    public Specification<Product> hasCompanyNameLike(String value) {
        return (root, query, builder) -> builder.like(root.get("company").get("name"), "%" + value + "%");
    }

}
