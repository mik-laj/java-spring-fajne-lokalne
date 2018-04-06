package fajnelokalne.demo.controller.app;

import fajnelokalne.demo.entity.Company;
import fajnelokalne.demo.filters.CompanyFilter;
import fajnelokalne.demo.service.CompanyManager;
import fajnelokalne.demo.service.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class AppCompanyController {
    @Autowired
    CompanyManager companyManager;

    @Autowired
    ProductManager productManager;


    @GetMapping("/")
    String list(
            @ModelAttribute("filter") CompanyFilter.FilterParameter filter,
            Model model,
            Pageable pageable
    ) {
        model.addAttribute("objectsList", companyManager.findAll(filter, pageable));
        return "company/list";
    }


    @GetMapping("/{id}")
    String view(
            @PathVariable("id") Company entity,
            Model model,
            Pageable pageable
    ) {
        model.addAttribute("object", entity);
        model.addAttribute("objectsList", productManager.findAllByCompany(entity, pageable));
        return "company/view";
    }
}
