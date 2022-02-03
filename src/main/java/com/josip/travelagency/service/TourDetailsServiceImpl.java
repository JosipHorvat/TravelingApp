package com.josip.travelagency.service;

import com.josip.travelagency.model.TourDetails;
import com.josip.travelagency.repository.TourDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TourDetailsServiceImpl implements TourDetailsService{

    @Autowired
    TourDetailsRepository tourDetailsRepository;

    @Override
    public TourDetails getById(long id) {
        return tourDetailsRepository.getById(id);
    }

    @Override
    public void saveOrUpdate(TourDetails tourDetails) {
        tourDetailsRepository.save(tourDetails);
    }

    @Override
    public void delete(long id) {
        tourDetailsRepository.deleteById(id);
    }
}
