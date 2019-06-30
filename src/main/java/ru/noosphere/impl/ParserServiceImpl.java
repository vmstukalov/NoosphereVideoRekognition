package ru.noosphere.impl;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import ru.noosphere.services.ParserService;

import java.io.IOException;

@Service("parserService")
public class ParserServiceImpl implements ParserService {


    public String getVideoURLbyNooSphereURL(String nooSphereURL) {

        Connection.Response response;
        String videoUrl = null;

        try {
            response = Jsoup.connect(nooSphereURL)
            .execute();

            Document document = response.parse();
            System.out.println(document.title());

            Element linkElement = document.selectFirst(".btn-success");
            String linkOrigin = linkElement.attr("href"); //ссыдка на источник с видео (archive.org),

            response = Jsoup.connect(linkOrigin).execute();

            Document videoPage = response.parse();
            videoUrl = videoPage.selectFirst("link[itemprop='embedUrl']").attr("href");
            return videoUrl;

        } catch (IOException e) {
            System.out.println("Не удалось подключиться");
            e.printStackTrace();
        }




        return videoUrl;
    }
}
