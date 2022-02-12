package com.josip.travelagency.service;

import com.josip.travelagency.model.Role;
import com.josip.travelagency.model.TAUser;
import com.josip.travelagency.repository.RoleRepository;
import com.josip.travelagency.repository.TAUserRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TAUserServiceImpl implements TAUserService{

    @Autowired
    private TAUserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void createNewAccount(TAUser user) {
        user.setEnabled(true);
        user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);

        Role role = new Role();
        role.setLogin(user.getLogin());
        role.setRole("ROLE_CLIENT");
        roleRepository.save(role);

        // TODO: 12/02/2022 in the future admin can make clients employees or i can add in signup form another password which will be one and unique. It needs to match, if not client will be made 
        // TODO: 12/02/2022 Another way of doing it can be a button in clients page. If pressed it will ask for password, when entered client will be promoted to employee 
    }

    @Override
    public boolean loginExists(String login) {

        return userRepository.existsByLogin(login);
    }
}
