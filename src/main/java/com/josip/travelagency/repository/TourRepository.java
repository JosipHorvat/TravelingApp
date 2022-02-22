package com.josip.travelagency.repository;

import com.josip.travelagency.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    //Custom query
    @Query(value = "select * from tour t where t.name like %:keyword% or t.duration like %:keyword%", nativeQuery = true)
    List<Tour> findByKeyword(@Param("keyword") String keyword);
}
