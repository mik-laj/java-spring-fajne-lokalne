package fajnelokalne.demo.filters;

import fajnelokalne.demo.entity.Company;
import fajnelokalne.demo.specifications.CompanySpecifications;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

@Component
public class CompanyFilter {

    @Data
    public static class FilterParameter {
        String name;
        Long city;
    }

    @Autowired
    CompanySpecifications specifications;

    public Specification<Company> apply(FilterParameter filters) {
        ArrayList<Specification<Company>> specs = new ArrayList<>();

        if (StringUtils.hasText(filters.getName())) {
            specs.add(specifications.hasNameLike(filters.getName()));
        }

        if (filters.getCity() != null && filters.getCity() > 0) {
            specs.add(specifications.hasCityId(filters.getCity()));
        }

        if (specs.size() == 0) {
            return null;
        }

        Specification<Company> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }

        return result;
    }
}
