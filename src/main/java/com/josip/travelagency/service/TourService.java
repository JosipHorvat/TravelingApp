package com.josip.travelagency.service;

import com.josip.travelagency.model.Tour;

import java.util.List;

public interface TourService {

    List<Tour> getAll();

    Tour getById(long id);

    void saveOrUpdate(Tour tour);

    void delete(long id);

    void addTourDetailsIfNotExists(Tour tour);

}
