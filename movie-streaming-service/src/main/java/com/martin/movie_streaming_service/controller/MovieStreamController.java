package com.martin.movie_streaming_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class MovieStreamController {

    public static final String VIDEO_DIRECTORY = "D:\\Users\\Usuario\\Desktop\\Micro\\";
    public static final Logger log = Logger.getLogger(MovieStreamController.class.getName());

    @Autowired
    private MovieCatalaogService movieCatalaogService;

    // para testear que devuelva el video, no usamos postman, ya q no devuelve videos
    @GetMapping("/stream/{videoPath}")  // recibiremos el nombre del video como igual esta almacenado
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String videoPath) throws FileNotFoundException {
        File file = new File(VIDEO_DIRECTORY+videoPath);
        if(file.exists()){ // basicamente si ese archivo existe lo retorna
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return  ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(("video/mp4")))
                    .body(inputStreamResource);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stream/with-id/{videoInfoId}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable Long videoInfoId) throws FileNotFoundException {
        String moviePath=movieCatalaogService.getMoviePath(videoInfoId); // llamamos al catalogService
        log.log(Level.INFO, "Resolved movie path = {0}", moviePath); // log para consola
        return streamVideo(moviePath);
    }
}
