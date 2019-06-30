package ru.noosphere.impl;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import ru.noosphere.services.RecognizerService;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.List;

@Service("recognizerService")
public class RecognizerServiceImpl implements RecognizerService {

    public void recognize(String filePath) throws IOException {

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
        }
        System.out.println(result.getUnrecognizedFaces().size() + " face(s) were unrecognized.");
        /*

        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image()
                        .withBytes(imageBytes))
                .withMaxLabels(10)
                .withMinConfidence(77F);


        try {

            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            List<Label> labels = result.getLabels();

            System.out.println("Detected labels for " + filePath);
            for (Label label: labels) {
                System.out.println(label.getName() + ": " + label.getConfidence().toString());
            }

        } catch (AmazonRekognitionException e) {
            e.printStackTrace();
        }
        */
    }
}
