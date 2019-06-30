package ru.noosphere.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.noosphere.services.VideoService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class StaticController {
    private VideoService videoService;

    @GetMapping
    public String index() {
        return "static/index";
    }

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }
}
