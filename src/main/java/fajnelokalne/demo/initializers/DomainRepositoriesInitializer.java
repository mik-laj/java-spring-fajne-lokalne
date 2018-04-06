package fajnelokalne.demo.initializers;

import fajnelokalne.demo.CollectorUtils;
import fajnelokalne.demo.entity.*;
import fajnelokalne.demo.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
@Profile("!production")
public class DomainRepositoriesInitializer {

    @Autowired
    CompanyRepository companyReposiory;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository tagRepository;

    public void run() throws Exception {
        List<City> cities = cityRepository.findAll();

        if (cities.size() == 0) {
            int city_count = 6;
            for (int i = 0; i < city_count; i++) {
                double dx = Math.random() * 4 - 2;
                double dy = Math.random() * 4 - 2;

                Point point = new Point(new BigDecimal(52.233333 + dx), new BigDecimal(21.016667 + dy));
                City city = new City(
                        String.format("city #%d", i),
                        point
                );
                cityRepository.save(city);
                cities.add(city);
            }
        }

        List<Tag> tags = tagRepository.findAll();

        if (tags.size() == 0) {
            tags = Stream.of(
                    "zielone",
                    "niebieskie",
                    "pomaranczowe",
                    "fioletowe"
            ).collect(CollectorUtils.toShuffledStream()).map(Tag::new).collect(toList());

            tagRepository.save(tags);
        }

        List<Company> companies = companyReposiory.findAll();

        if (companies.size() == 0) {
            companies = new ArrayList<>();
            for (int i = 0; i < 18; i++) {
                String name = String.format("Company #%s", i);
                City city = cities.get(i * 15 % cities.size());
                String format = String.format("%09d", i);

                Company company = new Company(name, city, format);
                companyReposiory.save(company);
                companies.add(company);
            }
        }

        List<Product> producties = productRepository.findAll();

        if (producties.size() == 0) {
            for (int i = 0; i < 18; i++) {
                Company company = companies.get(i * 15 % cities.size());
                String name = String.format("Product #%s", i);
                Product product = new Product(name, company);
                product.setDescription("Lorem ipsum");
                tags.stream()
                        .collect(CollectorUtils.toShuffledStream())
                        .limit(i % 3)
                        .forEach(product::addTag);
                productRepository.save(product);
            }
        }

    }
}
