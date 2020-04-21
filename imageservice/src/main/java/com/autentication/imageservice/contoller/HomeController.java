package com.autentication.imageservice.contoller;


import com.autentication.imageservice.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private Environment environment;

    @RequestMapping("/images")
    public List<Image> getImages() {
        List<Image> images = Arrays.asList(
                new Image(1, "Homer goes something, something", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"),
                new Image(2, "What happened when the man hit you", "https://www.imdb.com/title/tt0096697/mediaviewer/rm1572128512"),
                new Image(3, "Is your name Homer Simpson", "https://www.imdb.com/title/tt0096697/mediaviewer/rm2221347328"));
        return images;

    }
}
