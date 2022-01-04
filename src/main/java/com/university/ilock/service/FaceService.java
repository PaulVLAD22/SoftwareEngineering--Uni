package com.university.ilock.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.ilock.Repository.DeviceRepository;
import com.university.ilock.dtos.FaceDetectResponseDto;
import com.university.ilock.dtos.FaceUnlockDto;
import com.university.ilock.model.Device;
import com.university.ilock.utils.Constants;
import com.university.ilock.utils.Secrets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FaceService {

    private static final double minConfidence = Constants.MinimumConfidenceLevel;
    private static final String faceListUrl = Secrets.faceListUrl;
    private static final String baseUrl = Secrets.faceBaseUrl;
    private static final String apiKey = Secrets.faceApiKey;
    private final DeviceRepository deviceRepository;
    private final RestTemplate restTemplate;


    @Autowired
    public FaceService(RestTemplateBuilder restTemplateBuilder,
                       DeviceRepository deviceRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.deviceRepository = deviceRepository;
    }

    public void EnableFaceUnlocking(long deviceId){
        Device device = deviceRepository.getById(deviceId);
        /// create face list
        String url = this.baseUrl+"/face/v1.0/facelists/"+this.faceListUrl+deviceId;
        String body = "{\"name\":\""+ this.faceListUrl+deviceId + "\"}";
        ///
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Ocp-Apim-Subscription-Key", this.apiKey);
        // build the request
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        // send POST request
        this.restTemplate.put(url, entity);

        //
        device.setIsFaceUnlockEnabled(true);
        deviceRepository.save(device);
    }

    public boolean AddFace(FaceUnlockDto faceUnlockDto){
        Device device = deviceRepository.getById(faceUnlockDto.getId());
        if(!faceUnlockDto.getPin().equals(deviceRepository.getById(faceUnlockDto.getId()).getPin())){
            return false;
        }
        /// add to face list
        String url = this.baseUrl+"/face/v1.0/facelists/" + this.faceListUrl + faceUnlockDto.getId() + "/persistedfaces";
        String body = "{\"url\":\""+ faceUnlockDto.getUrl() + "\"}";
        ///
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Ocp-Apim-Subscription-Key", this.apiKey);
        // build the request
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        // send POST request
        this.restTemplate.postForLocation(url, entity);
        ///
        return true;
    }

    public boolean Validate(FaceUnlockDto faceUnlockDto) throws IOException {

        //// register face

        String url1 = this.baseUrl+"/face/v1.0/detect?returnFaceId=true";
        String body1 = "{\"url\":\""+ faceUnlockDto.getUrl() + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Ocp-Apim-Subscription-Key", this.apiKey);
        // build the request
        HttpEntity<String> entity = new HttpEntity<>(body1, headers);

        // send POST request
        ResponseEntity<String>  response = this.restTemplate.postForEntity(url1, entity, String.class);
        if(response.getStatusCode() != HttpStatus.OK){
            return false;
        }
        String res = response.getBody();
        final JsonNode node = new ObjectMapper().readValue(res, JsonNode.class);

        if (!node.has(0)) {
            return false;
        }
        String faceId = node.get(0).get("faceId").asText();

        String url2 = this.baseUrl + "/face/v1.0/findsimilars";

        String body2 = "{\"faceId\":\""+ faceId + "\"," + "\"faceListId\":\""+ this.faceListUrl + faceUnlockDto.getId() + "\"}";

        HttpEntity<String> entity2 = new HttpEntity<>(body2, headers);

        // send POST request
        ResponseEntity<String>  response2 = this.restTemplate.postForEntity(url2, entity2, String.class);
        String res2 = response2.getBody();
        final List<FaceDetectResponseDto> responses = new ObjectMapper().readValue(res2, new TypeReference<List<FaceDetectResponseDto>>(){});
        for(FaceDetectResponseDto faceDetectResponseDto : responses){
            if(faceDetectResponseDto.getConfidence() > minConfidence){
                Device device = deviceRepository.getById(faceUnlockDto.getId());
                device.setIsLocked(false);
                deviceRepository.save(device);
                return true;
            }
        }
        return false;
    }
}
