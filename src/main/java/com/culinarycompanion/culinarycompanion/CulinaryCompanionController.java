package com.culinarycompanion.culinarycompanion;


import Classes.RetrieveTechniques;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.*;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class,
        SpringDataWebAutoConfiguration.class})
@RestController
@RequestMapping("/")
public class CulinaryCompanionController {

    @CrossOrigin
    @GetMapping("/techniques")
    public String returnTechEndPoint(@RequestParam String searchTag){
        RetrieveTechniques newTechniqueAttempt = new RetrieveTechniques();
        return newTechniqueAttempt.getTechniqueByName(searchTag).toString();
    }

    @CrossOrigin
    @GetMapping("/techniques/Url")
    public String returnTechEndPointTest(@RequestParam String searchName){
        RetrieveTechniques newTechniqueAttempt = new RetrieveTechniques();
        return newTechniqueAttempt.getTechniqueUrlByName(searchName).toString();
    }

    @CrossOrigin
    @RequestMapping("")
    public String blank() {
        return "Culinary Companion base-page :)";
    }
}
