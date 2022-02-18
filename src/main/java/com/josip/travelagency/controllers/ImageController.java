package com.josip.travelagency.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.josip.travelagency.model.Image;
import com.josip.travelagency.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    @Value("${uploadDir}")
    private String uploadFolder;

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping(value = {"/image-home"})
    public String addProductPage() {
        return "index";
    }

    @PostMapping("/image/saveImageDetails")
    public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("name") String name,
                                                        @RequestParam("description") String description, Model model, HttpServletRequest request
            ,final @RequestParam("image") MultipartFile file) {
        try {
            //String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
            String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, fileName).toString();
            if (fileName == null || fileName.contains("..")) {
                model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
            }
            String[] names = name.split(",");
            String[] descriptions = description.split(",");

            try {
                File dir = new File(uploadDirectory);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // Save the file locally
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte[] imageData = file.getBytes();
            Image image = new Image();
            image.setName(names[0]);
            image.setImage(imageData);
            image.setDescription(descriptions[0]);
            imageRepository.save(image);

            return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/image/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Image> image)
            throws ServletException, IOException {
        image = imageRepository.getImageById(id);
        // response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(image.get().getImage());
        response.getOutputStream().close();
    }

    @GetMapping("/image/imageDetails")
    String showProductDetails(@RequestParam("id") Long id, Optional<Image> image, Model model) {
        try {
            if (id != 0) {
                image = imageRepository.getImageById(id);

                if (image.isPresent()) {
                    model.addAttribute("id", image.get().getId());
                    model.addAttribute("description", image.get().getDescription());
                    model.addAttribute("name", image.get().getName());
                    return "imagedetails";
                }
                return "redirect:/showOffer";
            }
            return "redirect:/showOffer";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/showOffer";
        }
    }

    @GetMapping("/image/show")
    String show(Model map) {
        List<Image> images = imageRepository.findAll();
        map.addAttribute("images", images);
        return "images";
    }
}

//popravit endpointove, nakon toga urediti foldere kao u travel agency
