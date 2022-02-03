package com.josip.travelagency.repository;

import com.josip.travelagency.model.TourDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourDetailsRepository extends JpaRepository<TourDetails, Long> {
}
