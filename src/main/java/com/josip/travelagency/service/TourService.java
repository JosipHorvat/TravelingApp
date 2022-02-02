package com.josip.travelagency.service;

import com.josip.travelagency.model.Tour;

import java.util.List;

public interface TourService {

    public List<Tour> getAll();

    public Tour getById(int id);

    public void saveOrUpdate(Tour tour);

    public void delete(int id);

}
