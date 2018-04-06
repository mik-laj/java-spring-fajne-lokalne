package fajnelokalne.demo.formdata;

import fajnelokalne.demo.entity.Company;
import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.entity.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
public class ProductFormData {
    @NotNull
    @Size(min = 2, max = 30)
    String name;

    ChoiceItem company;

    List<String> tags = new ArrayList<>();

    String description;

    public ProductFormData(Product product) {
        this.name = product.getName();
        Company company = product.getCompany();

        if (company != null) {
            this.company = new ChoiceItem(company.getId(), company.getName());
        }

        Set<Tag> tags = product.getTags();

        if (tags != null) {
            this.tags = product.getTags().stream().map(Tag::getName).collect(toList());
        }

        this.description = product.getDescription();
    }
}
