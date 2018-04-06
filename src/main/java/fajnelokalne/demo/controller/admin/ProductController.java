package fajnelokalne.demo.controller.admin;

import fajnelokalne.demo.filters.ProductFilter;
import fajnelokalne.demo.formdata.ProductFormData;
import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.service.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    ProductManager productManager;

    @Autowired
    ConversionService conversionService;

    @GetMapping("")
    String list(
            @ModelAttribute("filter") ProductFilter.FilterParameter filter,
            Model model,
            Pageable pageable
    ) {
        model.addAttribute("objectsList", productManager.findAll(filter, pageable));
        return "admin/product/list";
    }


    @GetMapping("/{id}")
    String view(
            @PathVariable("id") Product product,
            Model model
    ) {
        model.addAttribute("object", product);
        return "admin/product/view";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    String add(
            @ModelAttribute("formData") ProductFormData formData
    ) {
        return "admin/product/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String add(
            @ModelAttribute("formData") @Valid ProductFormData formData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/product/add";
        }
        Product product = productManager.create(formData);
        return String.format("redirect:/admin/product/%d/", product.getId());
    }

    @GetMapping("/{id}/edit")
    String edit(
            @PathVariable("id") Product product,
            Model model
    ) {
        model.addAttribute("formData", new ProductFormData(product));
        model.addAttribute("object", product);
        return "admin/product/edit";
    }

    @PostMapping("/{id}/edit")
    String edit(
            @ModelAttribute("formData") ProductFormData formData,
            BindingResult bindingResult,
            @PathVariable("id") Product product,
            Model model
    ) {
        model.addAttribute("object", product);
        productManager.update(product, formData);
        if (bindingResult.hasErrors()) {
            return "admin/product/edit";
        }

        return String.format("redirect:/admin/product/%d/", product.getId());
    }

    @RequestMapping(value = "/{id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    String delete(
            HttpServletRequest request,
            @PathVariable("id") Product product,
            Model model
    ) {
        boolean isPost = request.getMethod().equals("POST");
        if (isPost) {
            productManager.delete(product);
            // TODO: Add Toast
            return "redirect:/admin/product/";
        }
        model.addAttribute("object", product);
        return "admin/product/delete";
    }

}
