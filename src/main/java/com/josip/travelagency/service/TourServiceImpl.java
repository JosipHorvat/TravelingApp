package com.josip.travelagency.service;

import com.josip.travelagency.model.TAUser;
import com.josip.travelagency.model.Tour;
import com.josip.travelagency.repository.TAUserRepository;
import com.josip.travelagency.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TourServiceImpl implements TourService{

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TAUserRepository userRepository;

    @Override
    public List<Tour> getAll() {
        return tourRepository.findAll();
    }

    @Override
    public Tour getById(long id) {
        return tourRepository.findById(id).get();
    }

    @Override
    public void saveOrUpdate(Tour tour) {
        tourRepository.save(tour);
    }

    @Override
    public void delete(long id) {
        tourRepository.deleteById(id);
    }

    @Override
    public void addUserToTour(long id, String login) {
        Tour tour = getById(id);
        if(tour.getUsers() == null){
            tour.setUsers(new ArrayList<>());
        }

        TAUser user = userRepository.findByLogin(login);
        if(user != null){
            tour.getUsers().add(user);
            saveOrUpdate(tour);
        }
    }


}
