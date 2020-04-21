package com.autentication.galleryservice.controller;


import com.autentication.galleryservice.model.Gallery;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment environment;


    @RequestMapping("/")
    public String home() {
        return "Hello from Gallery Service running at port: " + environment.getProperty("local.server.port");
    }

    @HystrixCommand(fallbackMethod = "fallback")
     @RequestMapping("/{id}")
        public Gallery getGallery(@PathVariable final int id){
        Gallery gallery=new Gallery();
        gallery.setId(id);

        List<Object> images=restTemplate.getForObject("http://image-service/images/", List.class);
        gallery.setImages(images);

        return gallery;

     }

     public Gallery fallback(int galleryId, Throwable hystrixCommand){
        return new Gallery(galleryId);
     }

     @RequestMapping("/admin")
    public String homeAdmin(){
         return "This is the admin area of Gallery service running at port: " + environment.getProperty("local.server.port");
     }


}
