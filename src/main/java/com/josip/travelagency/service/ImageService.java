package com.josip.travelagency.service;

import com.josip.travelagency.model.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {

    void saveImage(Image image);

    List<Image> getAllActiveImages();

    Optional<Image> getImageById(Long id);
}
