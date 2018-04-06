package fajnelokalne.demo.controller.api;

import fajnelokalne.demo.domain.VoteResponse;
import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.entity.User;
import fajnelokalne.demo.entity.Vote;
import fajnelokalne.demo.repository.ProductRepository;
import fajnelokalne.demo.repository.VoteRepository;
import fajnelokalne.demo.service.SecurityService;
import fajnelokalne.demo.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppVoteController {
    @Autowired
    VoteRepository voteRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserManager userManager;

    @Autowired
    SecurityService securityService;

    @PostMapping(value = "/api/vote/product/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<VoteResponse> view(
            @PathVariable("id") Product product,
            @RequestParam(value = "value",defaultValue = "-1") Integer value
    ) {
//        String username = securityService.findLoggedInUsername();
//        if(!StringUtils.isEmpty(username) && value > 0) {
//            User user = userManager.findByUsername(username);
//            if(!voteRepository.existsBycreatedByAndProduct(user, product)){
                product.setVoteCount(product.getVoteCount() + 1);
                product.setVoteValue(product.getVoteValue() + value);
                productRepository.save(product);
                voteRepository.save(new Vote(product, value, null));
//            }
//        }
        return ResponseEntity.ok(new VoteResponse(product.getVoteCount(), product.getVoteValue()));
    }
}
