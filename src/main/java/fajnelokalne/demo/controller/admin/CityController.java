package fajnelokalne.demo.controller.admin;

import fajnelokalne.demo.filters.CityFilter;
import fajnelokalne.demo.formdata.CityFormData;
import fajnelokalne.demo.entity.City;
import fajnelokalne.demo.service.CityManager;
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
@RequestMapping("/admin/city")
public class CityController {
    @Autowired
    CityManager cityManager;

    @Autowired
    ConversionService conversionService;

    @GetMapping("")
    String list(
            @ModelAttribute("filter") CityFilter.FilterParameter filter,
            Model model,
            Pageable pageable
    ) {
        model.addAttribute("objectsList", cityManager.findAll(filter, pageable));
        return "admin/city/list";
    }


    @GetMapping("/{id}")
    String view(
            @PathVariable("id") City city,
            Model model
    ) {
        model.addAttribute("object", city);
        return "admin/city/view";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    String add(
            @ModelAttribute("formData") CityFormData formData
    ) {
        return "admin/city/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String add(
            @ModelAttribute("formData") @Valid CityFormData formData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/city/add";
        };
        City city = cityManager.add(formData);
        return String.format("redirect:/admin/city/%d/", city.getId());
    }

    @GetMapping("/{id}/edit")
    String edit(
            @PathVariable("id") City city,
            Model model
    ) {
        model.addAttribute("formData", new CityFormData(city));
        model.addAttribute("object", city);
        return "admin/city/edit";
    }

    @PostMapping("/{id}/edit")
    String edit(
            @ModelAttribute("formData") CityFormData formData,
            BindingResult bindingResult,
            @PathVariable("id") City city,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/city/edit";
        }
        model.addAttribute("object", city);
        cityManager.update(city, formData);
        return String.format("redirect:/admin/city/%d/", city.getId());
    }

    @RequestMapping(value = "/{id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    String delete(
            HttpServletRequest request,
            @PathVariable("id") City city,
            Model model
    ) {
        boolean isPost = request.getMethod().equals("POST");
        if (isPost) {
            cityManager.delete(city);
            // Add Toast
            return "redirect:/admin/city/";
        }
        model.addAttribute("object", city);
        return "admin/city/delete";
    }

}
