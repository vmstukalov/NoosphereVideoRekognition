package ru.noosphere.controllers;

import org.bytedeco.javacv.FrameFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.noosphere.entities.Person;
import ru.noosphere.services.RecognizerService;
import ru.noosphere.services.VideoService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class StaticController {
    private VideoService videoService;
    private RecognizerService recognizerService;

    @GetMapping
    public String index() {
        return "static/index";
    }


    @GetMapping("recognize")
    @ResponseBody
    public List<Person> recognize() {

        try {
            return recognizerService.recognize("/Users/vitaly/Downloads/k.jpg");

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }


    }

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }

    @Autowired
    public void setRecognizerService(RecognizerService recognizerService) {
        this.recognizerService = recognizerService;
    }
}
