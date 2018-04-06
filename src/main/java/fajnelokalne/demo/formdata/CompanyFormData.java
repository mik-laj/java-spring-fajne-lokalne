package fajnelokalne.demo.formdata;

import fajnelokalne.demo.entity.City;
import fajnelokalne.demo.entity.Company;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.awt.*;

@Data
@NoArgsConstructor
public class CompanyFormData {
    @Size(min=3)
    @NotNull
    String name;

    ChoiceItem city;

    @NotNull
    @Size(min=9, max=9)
    String krs;

    @NotNull
    String description;

    public CompanyFormData(Company entity) {
        this.name = entity.getName();
        City city = entity.getCity();
        if(city != null) {
            this.city = new ChoiceItem(city.getId(), city.getName());
        }
        this.krs = entity.getKrs();
    }
}
