package fajnelokalne.demo.service;

import fajnelokalne.demo.entity.City;
import fajnelokalne.demo.entity.Company;
import fajnelokalne.demo.filters.CompanyFilter;
import fajnelokalne.demo.formdata.CompanyFormData;
import fajnelokalne.demo.repository.CityRepository;
import fajnelokalne.demo.repository.CompanyRepository;
import fajnelokalne.demo.specifications.CompanySpecifications;
import io.springlets.format.EntityResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyManager implements EntityResolver<Company, Long> {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CompanySpecifications companySpecifications;

    @Autowired
    CompanyFilter companyFilter;


    public Page<Company> findAll(CompanyFilter.FilterParameter filter, Pageable pageable) {
        Specification<Company> specification = companyFilter.apply(filter);
        return companyRepository.findAll(specification, pageable);
    }

    public Page<Company> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public Page<Company> findAll(Specification<Company> specification, Pageable pageable) {
        return companyRepository.findAll(specification, pageable);
    }

    public Page<Company> search(String name, Pageable pageable) {
        name = "%" + name + "%";
        return companyRepository.findByNameLike(name, pageable);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company getOne(Long aLong) {
        return companyRepository.getOne(aLong);
    }

    public Company save(Company s) {
        return companyRepository.save(s);
    }

    public void delete(Company product) {
        companyRepository.delete(product);
    }

    @Override
    public Company findOne(Long id) {
        return companyRepository.findOne(id);
    }

    @Override
    public Class<Company> getEntityType() {
        return Company.class;
    }

    @Override
    public Class<Long> getIdType() {
        return Long.class;
    }

    public Company save(CompanyFormData formData) {
        Company company = new Company();
        bindForm(formData, company);
        companyRepository.save(company);
        return company;
    }

    private void bindForm(CompanyFormData formData, Company company) {
        company.setName(formData.getName());
        if (formData.getCity() != null && formData.getCity().getId() != null && formData.getCity().getId() > 0) {
            company.setCity(cityRepository.findOne(formData.getCity().getId()));
        }
        company.setKrs(formData.getKrs());
    }

    public void save(Company entity, CompanyFormData formData) {
        bindForm(formData, entity);
        companyRepository.save(entity);
    }

    public Page<Company> findAllByCity(City city, Pageable pageable) {
        return companyRepository.findAllByCity(city, pageable);
    }
}
