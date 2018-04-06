package fajnelokalne.demo.service;

import fajnelokalne.demo.entity.Company;
import fajnelokalne.demo.entity.Tag;
import fajnelokalne.demo.filters.ProductFilter;
import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.formdata.ProductFormData;
import fajnelokalne.demo.repository.CompanyRepository;
import fajnelokalne.demo.repository.ProductRepository;
import fajnelokalne.demo.repository.TagRepository;
import fajnelokalne.demo.specifications.ProductSpecifications;
import io.springlets.format.EntityResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Component
public class ProductManager implements EntityResolver<Product, Long> {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    ProductSpecifications productSpecifications;

    @Autowired
    ProductFilter productFilter;

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> findAll(ProductFilter.FilterParameter filter, Pageable pageable) {
        Specification<Product> specification = productFilter.apply(filter);
        return productRepository.findAll(specification, pageable);
    }

    public Page<Product> search(String name, Pageable pageable) {
        name = "%" + name + "%";
        return productRepository.findByNameLike(name, pageable);
    }

    public Product save(Product s) {
        return productRepository.save(s);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Class<Product> getEntityType() {
        return Product.class;
    }

    @Override
    public Class<Long> getIdType() {
        return Long.class;
    }

    public Product create(ProductFormData formData) {
        Product p = new Product();
        copyProductFormData(p, formData);
        productRepository.save(p);
        return p;
    }

    private void copyProductFormData(Product p, ProductFormData formData) {
        p.setName(formData.getName());
        if(formData.getCompany() != null) {
            p.setCompany(companyRepository.getOne(formData.getCompany().getId()));
        }
        if(formData.getTags() != null) {
            formData.getTags().stream()
                    .map(tagRepository::findOneByName)
                    .forEach(p::addTag);
        }
        p.setDescription(formData.getDescription());
    }

    public Product update(Product product, ProductFormData formData) {
        copyProductFormData(product, formData);
        productRepository.save(product);
        return product;
    }

    public Page<Product> findAllByTag(Tag tag, Pageable pageable) {
        return productRepository.findAllByTags(tag, pageable);
    }

    public Page<Product> findAllByCompany(Company company, Pageable pageable) {
        return productRepository.findAllByCompany(company, pageable);
    }

}
