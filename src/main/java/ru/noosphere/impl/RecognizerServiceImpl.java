package ru.noosphere.impl;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.noosphere.entities.Person;
import ru.noosphere.services.RecognizerService;
import ru.noosphere.services.repo.PersonRepo;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("recognizerService")
public class RecognizerServiceImpl implements RecognizerService {

    private PersonRepo personRepo;

    public List<Person> recognize(String filePath) throws IOException {

        List<Person> personList = new ArrayList<>();

        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIA4ZLQMXII7XQNESGG", "FrCQxPKvReoYScaeugBmu1XeqCjalDvrJ9DjX/WO");


        ByteBuffer imageBytes;
        try (InputStream inputStream = new FileInputStream(new File(filePath))) {
            imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        }


        //AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();
        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard()
                .withRegion(Regions.EU_CENTRAL_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        RecognizeCelebritiesRequest request = new RecognizeCelebritiesRequest()
                .withImage(new Image()
                        .withBytes(imageBytes));

        System.out.println("Looking for celebrities in image " + filePath + "\n");

        RecognizeCelebritiesResult result=rekognitionClient.recognizeCelebrities(request);

        //Display recognized celebrity information
        List<Celebrity> celebs=result.getCelebrityFaces();
        System.out.println(celebs.size() + " celebrity(s) were recognized.\n");

        for (Celebrity celebrity: celebs) {
            System.out.println("Celebrity recognized: " + celebrity.getName());
            System.out.println("Celebrity ID: " + celebrity.getId());
            BoundingBox boundingBox=celebrity.getFace().getBoundingBox();
            System.out.println("position: " +
                    boundingBox.getLeft().toString() + " " +
                    boundingBox.getTop().toString());
            System.out.println("Further information (if available):");
            for (String url: celebrity.getUrls()){
                System.out.println(url);
            }
            System.out.println();

            try {
                Person person = new Person();
                person.setCelebrityId(celebrity.getId());
                person.setName(celebrity.getName());

                //ставится последний url
                for (String url: celebrity.getUrls()){
                    System.out.println(url);
                    person.setImdb(url);
                }

                personRepo.save(person);
                personList.add(person);
            } catch (Exception ignored){ }

        }
        System.out.println(result.getUnrecognizedFaces().size() + " face(s) were unrecognized.");

        return personList;
    }

    @Autowired
    public void setPersonRepo(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }
}
