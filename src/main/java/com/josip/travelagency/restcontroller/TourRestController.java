package com.josip.travelagency.restcontroller;

import com.josip.travelagency.model.Tour;
import com.josip.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/tours/{id}")
    public ResponseEntity<Tour> getTour(@PathVariable long id) {
        Tour tour = tourService.getById(id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Method", "getTour");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(tour);
    }

    @PostMapping(path = "/tours", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Tour> saveTour(@RequestBody Tour tour) {
        tour.setId(0L);
        tourService.saveOrUpdate(tour);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Method", "saveTour");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .body(tour);
    }

    @PutMapping("/tours")
    public ResponseEntity<Void> editTour(@RequestBody Tour tour) {
        tourService.saveOrUpdate(tour);
        System.out.println("Tour is edited with postman");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Method", "editTour");

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .headers(httpHeaders)
                .build();
    }

    @DeleteMapping("/tours/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable long id) {
        Tour tour = tourService.getById(id);
        tourService.delete(tour.getId());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Method", "deleteTour");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(null);
    }
}
