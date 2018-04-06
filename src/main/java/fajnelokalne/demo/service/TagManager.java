package fajnelokalne.demo.service;

import fajnelokalne.demo.entity.Tag;
import fajnelokalne.demo.repository.TagRepository;
import io.springlets.format.EntityResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagManager implements EntityResolver<Tag, String> {

    @Autowired
    TagRepository tagRepository;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Page<Tag> search(String name, Pageable pageable) {
        String keyword = "%" + name + "%";
        return tagRepository.findAllByNameLike(keyword, pageable);
    }

    @Override
    public Tag findOne(String s) {
        return tagRepository.findOneByName(s);
    }

    @Override
    public Class<Tag> getEntityType() {
        return Tag.class;
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }
}
