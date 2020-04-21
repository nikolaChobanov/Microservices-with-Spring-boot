package com.autentication.galleryservice.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class Gallery {

    private int id;
    private List<Object> images;

    public Gallery(int id) {
        this.id = id;
    }
}
