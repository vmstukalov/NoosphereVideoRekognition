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

    @GetMapping("rek")
    @ResponseBody
    public String rek(){
        String video = "/Users/g3/Downloads/cut3.mp4";
        File videoFile = new File(video);
        String pathToFrames = "/Users/g3/Downloads/frames/" + videoFile.getName() + "/";
        List<String> fileNames = new ArrayList<String>();
        try {
            fileNames = videoService.separateToFrames(videoFile, pathToFrames, 1000);
        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }

        //дальше можем брать каждый пятый из списка
        //fileNames.get(i);

        return "ok";
    }

    @GetMapping("download")
    @ResponseBody
    public String download(){
        String url = "https://archive.org/download/OurGangSilentHighSociety1924/HighSociety.mp4";
        File file = new File(url);
        try {
            videoService.downloadUsingStream(url, "/Users/g3/Downloads/" + file.getName());
        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }

        return "ok";

    }

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }
}
