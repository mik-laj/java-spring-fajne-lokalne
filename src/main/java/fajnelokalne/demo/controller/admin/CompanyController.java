package fajnelokalne.demo.controller.admin;

import fajnelokalne.demo.entity.Company;
import fajnelokalne.demo.filters.CompanyFilter;
import fajnelokalne.demo.formdata.CompanyFormData;
import fajnelokalne.demo.service.CompanyManager;
import io.springlets.data.web.select2.Select2DataSupport;
import io.springlets.data.web.select2.Select2DataWithConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/company")
public class CompanyController {
    @Autowired
    CompanyManager companyManager;

    @GetMapping("")
    String list(
            @ModelAttribute("filter") CompanyFilter.FilterParameter filter,
            Model model,
            Pageable pageable
    ) {
        model.addAttribute("objectsList", companyManager.findAll(filter, pageable));
        return "admin/company/list";
    }


    @GetMapping("/{id}")
    String view(
            @PathVariable("id") Company entity,
            Model model
    ) {
        model.addAttribute("object", entity);
        return "admin/company/view";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    String add(
            @ModelAttribute("formData") CompanyFormData formData
    ) {
        return "admin/company/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String add(
            @ModelAttribute("formData") @Valid CompanyFormData formData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/company/add";
        }
        Company entity = companyManager.save(formData);
        return String.format("redirect:/admin/company/%d/", entity.getId());
    }

    @GetMapping("/{id}/edit")
    String edit(
            @PathVariable("id") Company entity,
            Model model
    ) {
        model.addAttribute("formData", new CompanyFormData(entity));
        model.addAttribute("object", entity);
        return "admin/company/edit";
    }

    @PostMapping("/{id}/edit")
    String edit(
            @ModelAttribute("formData") CompanyFormData formData,
            BindingResult bindingResult,
            @PathVariable("id") Company entity,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/company/edit";
        }
        model.addAttribute("object", entity);
        companyManager.save(entity, formData);
        return String.format("redirect:/admin/company/%d/", entity.getId());
    }

    @RequestMapping(value = "/{id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    String delete(
            HttpServletRequest request,
            @PathVariable("id") Company entity,
            Model model
    ) {
        boolean isPost = request.getMethod().equals("POST");
        if (isPost) {
            companyManager.delete(entity);
            // Add Toast
            return "redirect:/admin/company/";
        }
        model.addAttribute("object", entity);
        return "admin/company/delete";
    }


}
