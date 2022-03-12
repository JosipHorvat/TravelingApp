package com.josip.travelagency.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "image")
public class Image extends MainEntity{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
