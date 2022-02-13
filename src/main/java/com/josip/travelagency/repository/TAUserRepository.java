package com.josip.travelagency.repository;

import com.josip.travelagency.model.TAUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TAUserRepository extends JpaRepository<TAUser, Long> {

     boolean existsByLogin(String login);

    TAUser findByLogin(String login);
}
