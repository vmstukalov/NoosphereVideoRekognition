package ru.noosphere.impl;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.noosphere.entities.Image;
import ru.noosphere.entities.Video;
import ru.noosphere.services.VideoService;
import ru.noosphere.services.repo.ImageRepo;
import ru.noosphere.services.repo.VideoRepo;

import javax.imageio.ImageIO;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

@Service("videoService")
public class VideoServiceImpl implements VideoService {

    private VideoRepo videoRepo;
    private ImageRepo imageRepo;


    public List<String> separateToFrames(Video video, String pathToSaveFrames, int everyFrame, boolean test) throws IOException {

            File videoFile = new File(video.getPath());

            FFmpegFrameGrabber g = new FFmpegFrameGrabber(videoFile);
            g.start();

            System.out.println(g.getFrameNumber());
            System.out.println(g.getLengthInFrames());


            int frameNumbers = g.getLengthInFrames();
            double duration = (double) g.getLengthInTime() / 1000;

            System.out.println("Фреймов: " + frameNumbers);
            System.out.println("Длина: " + duration + "мс");

            //int screenshotCount = (int) (frameNumbers / everyFrame);

            List<String> fileNames = new ArrayList<String>();

            Java2DFrameConverter converter = new Java2DFrameConverter();
            for (int i = 0; i < frameNumbers; i = i + everyFrame) {
                if (test){
                    if (i < 57600 || i > 73000){
                        continue;
                    }
                }
                if (i > 0) g.setFrameNumber(i);
                    Frame frame = g.grabImage();

                    File file = new File(pathToSaveFrames + System.currentTimeMillis() + ".jpg");
                    boolean createDirs = file.mkdirs();

                    fileNames.add(file.getAbsolutePath());

                    ImageIO.write(converter.convert(frame), "jpg", file);
                    /*try {
                        sleep(sleep);
                    } catch (Exception e){
                        e.printStackTrace();
                    }*/
            }

            g.stop();

            List<Image> images = new ArrayList<Image>();
            for (String fileName : fileNames){
                Image image  = new Image();
                image.setPath(fileName);
                image.setVideo(video);
                images.add(image);
            }
            imageRepo.save(images);

            return fileNames;
    }

    // качаем файл с помощью Stream
    public void downloadUsingStream(Video video, String file) throws IOException{
        URL url = new URL(video.getFileUrl());
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
        video.setPath(file);
        save(video);
    }

    public Video save(Video video) {
        return videoRepo.save(video);
    }

    @Autowired
    public void setVideoRepo(VideoRepo videoRepo) {
        this.videoRepo = videoRepo;
    }

    @Autowired
    public void setImageRepo(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }
}
