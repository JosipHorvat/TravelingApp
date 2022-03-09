package com.josip.travelagency.service;

import com.josip.travelagency.model.Tour;
import com.josip.travelagency.repository.TourRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class TourServiceImplTest {

    @Mock
    private TourRepository tourRepository;

    @InjectMocks
    private TourService tourService = new TourServiceImpl();

    @Captor
    private ArgumentCaptor valueCaptor;

    @Test
    @DisplayName("GIVEN two tour records exist in db" +
            " WHEN all tours are requested" +
            " THEN then two tours are returned")
    void getAllTours(){

        List<Tour> expectedTours = Arrays.asList(new Tour(), new Tour());

        when(tourRepository.findAll()).thenReturn(expectedTours);

        final List<Tour> actualTours = tourService.getAll();

        verify(tourRepository,times(1)).findAll();
        assertEquals(expectedTours,actualTours);
    }

   }
