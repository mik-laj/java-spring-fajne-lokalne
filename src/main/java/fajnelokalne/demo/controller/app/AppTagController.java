package fajnelokalne.demo.controller.app;

import fajnelokalne.demo.entity.Company;
import fajnelokalne.demo.entity.Tag;
import fajnelokalne.demo.filters.CompanyFilter;
import fajnelokalne.demo.service.CompanyManager;
import fajnelokalne.demo.service.ProductManager;
import fajnelokalne.demo.service.TagManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tag")
public class AppTagController {
    @Autowired
    TagManager tagManager;

    @Autowired
    ProductManager productManager;

    @GetMapping("")
    String list(
            Model model
    ) {
        model.addAttribute("objectsList", tagManager.findAll());
        return "tag/list";
    }


    @GetMapping("/{id}")
    String view(
            @PathVariable("id") Tag entity,
            Pageable pageable,
            Model model
    ) {
        model.addAttribute("object", entity);
        model.addAttribute("objectsList", productManager.findAllByTag(entity, pageable));
        return "tag/view";
    }
}
