package com.josip.travelagency.service;

import com.josip.travelagency.model.Tour;
import com.josip.travelagency.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TourServiceImpl implements TourService{

    @Autowired
    private TourRepository tourRepository;

    @Override
    public List<Tour> getAll() {
        return tourRepository.findAll();
    }

    @Override
    public Tour getById(long id) {
        return tourRepository.getById(id);
    }

    @Override
    public void saveOrUpdate(Tour tour) {
        tourRepository.save(tour);
    }

    @Override
    public void delete(long id) {
        tourRepository.deleteById(id);
    }

}
