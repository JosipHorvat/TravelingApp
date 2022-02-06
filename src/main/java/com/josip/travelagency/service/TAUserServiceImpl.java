package com.josip.travelagency.service;

import com.josip.travelagency.model.TAUser;
import com.josip.travelagency.repository.TAUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TAUserServiceImpl implements TAUserService{

    @Autowired
    TAUserRepository userRepository;

    @Override
    public TAUser getById(long id) {
        return userRepository.getById(id);
    }
}
