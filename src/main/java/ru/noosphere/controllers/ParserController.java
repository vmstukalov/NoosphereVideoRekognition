package ru.noosphere.controllers;

import org.bytedeco.javacv.FrameFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.noosphere.entities.Image;
import ru.noosphere.entities.NooSphereURL;
import ru.noosphere.entities.Person;
import ru.noosphere.entities.Video;
import ru.noosphere.services.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("parser")
public class ParserController {

    private NooSphereUrlService nooSphereUrlService;
    private ParserService parserService;
    private VideoService videoService;
    private ImageService imageService;
    private RecognizerService recognizerService;

    @PostMapping(value = "parse", produces = "application/json")
    public String parse(@RequestParam("link") String link, Model uiModel) {

        List<Person> personList = new ArrayList<>();

        NooSphereURL nooSphereURL = new NooSphereURL();
        nooSphereURL.setValue(link);

        String videoUrl = parserService.getVideoURLbyNooSphereURL(nooSphereURL.getValue());

        System.out.println(videoUrl);

        Video video = new Video();
        video.setNooSphereUrl(link);
        video.setFileUrl(videoUrl);

        //videoService.save(video);

        File videoFile = new File(videoUrl);
        String savePath = "/Users/g3/Downloads/" + videoFile.getName();
        String pathToFrames = "/Users/g3/Downloads/frames/" + videoFile.getName() + "/";
        videoFile = new File(savePath);

        try {
            videoService.downloadUsingStream(video, savePath);
            videoService.separateToFrames(video, pathToFrames, 60, true);
        } catch (Exception e){
            //return "error: Ошибка скачивания видео";
            e.printStackTrace();
        }

        List<Image> images = imageService.getNotScannedImages();

        for (Image image : images) {

            try {
                //personList = recognizerService.recognize(image.getPath());
                System.out.println("Распознаем: " + image.getPath());
                image.setScanned(true);
                imageService.save(image);

            } catch (Exception e) {
                e.printStackTrace();
            }



        }

        uiModel.addAttribute("personList", personList);
        return "static/result";
        //return personList;
    }



    @Autowired
    public void setNooSphereUrlService(NooSphereUrlService nooSphereUrlService) {
        this.nooSphereUrlService = nooSphereUrlService;
    }

    @Autowired
    public void setParserService(ParserService parserService) {
        this.parserService = parserService;
    }

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setRecognizerService(RecognizerService recognizerService) {
        this.recognizerService = recognizerService;
    }
}
