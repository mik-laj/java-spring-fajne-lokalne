package fajnelokalne.demo.controller.admin;

import java.io.IOException;
import java.util.stream.Collectors;

import fajnelokalne.demo.controller.app.FileController;
import fajnelokalne.demo.entity.Image;
import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.repository.ImageRepository;
import fajnelokalne.demo.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping()
public class ProductImageUploadController {

    @Autowired
    private StorageService storageService;

    @Autowired
    ImageRepository imageRepository;

    @GetMapping("/admin/images")
    public String listFiles(@ModelAttribute("object") Product product, Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll()
                .map(path -> MvcUriComponentsBuilder
                        .fromMethodName(FileController.class, "serveFile", path.getFileName())
                        .build().toString())
                .collect(toList()));

        return "admin/image/list-files";
    }

    @GetMapping("/admin/product/{id}/image")
    public String uploadForm(@ModelAttribute("object") Product product, Model model) throws IOException {

//        model.addAttribute("files", storageService.loadAll().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(ProductImageUploadController.class,
//                        "serveFile", path.getFileName().toString()).build().toString())
//                .collect(Collectors.toList()));

        return "admin/image/upload-form";
    }

    @PostMapping("/admin/product/{id}/image")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Product product,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);

        String filename = file.getOriginalFilename();
        String fileUrl = MvcUriComponentsBuilder
                .fromMethodName(FileController.class, "serveFile", filename)
                .build().toString();

        imageRepository.save(new Image(product, fileUrl));

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/admin/product/" + product.getId();
    }

}