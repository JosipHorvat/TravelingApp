package com.josip.travelagency.service;

import com.josip.travelagency.model.TAUser;

public interface TAUserService {


     void createNewAccount(TAUser user);

     boolean loginExists(String login);
}
