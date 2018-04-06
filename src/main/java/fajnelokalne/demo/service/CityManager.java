package fajnelokalne.demo.service;

import fajnelokalne.demo.entity.City;
import fajnelokalne.demo.entity.Point;
import fajnelokalne.demo.filters.CityFilter;
import fajnelokalne.demo.formdata.CityFormData;
import fajnelokalne.demo.repository.CityRepository;
import fajnelokalne.demo.specifications.CitySpecifications;
import io.springlets.format.EntityResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CityManager implements EntityResolver<City, Long> {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CitySpecifications citySpecifications;

    @Autowired
    CityFilter cityFilter;


    public Page<City> findAll(CityFilter.FilterParameter filter, Pageable pageable) {
        Specification<City> specification = cityFilter.apply(filter);
        return cityRepository.findAll(specification, pageable);
    }

    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    public Page<City> findAll(Specification<City> specification, Pageable pageable) {
        return cityRepository.findAll(specification, pageable);
    }

    public Page<City> search(String name, Pageable pageable) {
        name = "%" + name + "%";
        return cityRepository.findByNameLike(name, pageable);
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City getOne(Long aLong) {
        return cityRepository.getOne(aLong);
    }

    public City save(City s) {
        return cityRepository.save(s);
    }

    public void delete(City product) {
        cityRepository.delete(product);
    }

    public void update(City city, CityFormData formData) {
        bindForm(city, formData);
        cityRepository.save(city);
    }

    public City add(CityFormData formData) {
        City city = new City();
        bindForm(city, formData);
        cityRepository.save(city);
        return city;
    }

    private void bindForm(City city, CityFormData formData) {
        city.setName(formData.getName());
        BigDecimal lng = new BigDecimal(formData.getLng());
        BigDecimal lat = new BigDecimal(formData.getLat());
        Point location = new Point(lat, lng);
        city.setLocation(location);
    }

    @Override
    public City findOne(Long id) {
        return cityRepository.findOne(id);
    }

    @Override
    public Class<City> getEntityType() {
        return City.class;
    }

    @Override
    public Class<Long> getIdType() {
        return Long.class;
    }
}
