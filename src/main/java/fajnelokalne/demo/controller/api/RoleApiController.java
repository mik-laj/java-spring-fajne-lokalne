package fajnelokalne.demo.controller.api;

import fajnelokalne.demo.entity.Role;
import fajnelokalne.demo.service.RoleManager;
import io.springlets.data.web.select2.Select2DataSupport;
import io.springlets.data.web.select2.Select2DataWithConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/role")
public class RoleApiController {

    @Autowired
    RoleManager role;

    @Autowired
    ConversionService conversionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "select2", value = "/s2")
    @ResponseBody
    public ResponseEntity<Select2DataSupport<Role>> select2(
            @RequestParam("q") String search,
            Pageable pageable
    ) {
        Page<Role> vets = role.search(search, pageable);
        String idExpression = "#{name}";
        Select2DataSupport<Role> select2Data = new Select2DataWithConversion<>(vets, idExpression, conversionService);
        return ResponseEntity.ok(select2Data);
    }
}
