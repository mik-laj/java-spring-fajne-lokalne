package fajnelokalne.demo.formdata;

import fajnelokalne.demo.entity.City;
import fajnelokalne.demo.entity.Point;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CityFormData {
    @Size(min=3)
    @NotNull
    String name;

    @NotNull
    String lat;

    @NotNull
    String lng;

    public CityFormData(City city) {
        this.name = city.getName();
        Point location = city.getLocation();

        if(location != null) {
            this.lat = location.getLat().toString();
            this.lng = location.getLng().toString();
        }
    }
}
