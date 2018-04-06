package fajnelokalne.demo.controller.api;

import fajnelokalne.demo.domain.CityApiItem;
import fajnelokalne.demo.entity.City;
import fajnelokalne.demo.service.CityManager;
import io.springlets.data.web.select2.Select2DataSupport;
import io.springlets.data.web.select2.Select2DataWithConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/api/city")
public class CityApiController {

    @Autowired
    CityManager cityManager;

    @Autowired
    ConversionService conversionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "select2", value = "/s2")
    @ResponseBody
    public ResponseEntity<Select2DataSupport<City>> select2(
            @RequestParam("q") String search,
            Pageable pageable
    ) {
        Page<City> vets = cityManager.search(search, pageable);
        String idExpression = "#{id}";
        Select2DataSupport<City> select2Data = new Select2DataWithConversion<>(vets, idExpression, conversionService);
        return ResponseEntity.ok(select2Data);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "alll", value = "/all")
    @ResponseBody
    public ResponseEntity<List<CityApiItem>> all(
    ) {
        List<CityApiItem> items = cityManager.findAll().stream().map(d -> {
            String name = conversionService.convert(d, String.class);
            String href = "/city/" + d.getId();
            String lat = conversionService.convert(d.getLocation().getLat(), String.class);
            String lng = conversionService.convert(d.getLocation().getLng(), String.class);
            CityApiItem.PointApiItem pointApiItem = new CityApiItem.PointApiItem(lat, lng);
            return new CityApiItem(name, href, pointApiItem);
        }).collect(toList());
        return ResponseEntity.ok(items);
    }


}
