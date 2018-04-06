package fajnelokalne.demo.filters;

import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.specifications.ProductSpecifications;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

@Component
public class ProductFilter {

    @Data
    public static class FilterParameter {
        String name;
        String company;
    }

    @Autowired
    ProductSpecifications specifications;

    public Specification<Product> apply(FilterParameter filters) {
        ArrayList<Specification<Product>> specs = new ArrayList<>();

        if (filters.getName() != null && filters.getName().trim().length() > 0) {
            specs.add(specifications.hasNameLike(filters.getName()));
        }

        if (StringUtils.hasText(filters.getCompany())) {
            specs.add(specifications.hasCompanyNameLike(filters.getCompany()));
        }

        if (specs.size() == 0) {
            return null;
        }

        Specification<Product> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }

        return result;
    }
}
