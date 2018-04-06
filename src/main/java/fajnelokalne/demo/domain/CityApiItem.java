package fajnelokalne.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityApiItem {
    String name;
    String link;
    PointApiItem point;

    @Data
    @AllArgsConstructor
    public static class PointApiItem {
        String lat;
        String lng;
    }
}
