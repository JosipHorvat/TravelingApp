package com.josip.travelagency.service;

import com.josip.travelagency.model.TourDetails;

public interface TourDetailsService {

    TourDetails getById(long id);
    void saveOrUpdate(TourDetails tourDetails);
    void delete(long id);
}
