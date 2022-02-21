package com.josip.travelagency.restcontroller;

import com.josip.travelagency.model.Tour;
import com.josip.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class TourRestController {

    @Autowired
    private TourService tourService;

    @GetMapping("/tours")
    public ResponseEntity<List<Tour>> getTours(){
    List<Tour> tours = tourService.getAll();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Method", "getTours");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(tours);
    }
}
