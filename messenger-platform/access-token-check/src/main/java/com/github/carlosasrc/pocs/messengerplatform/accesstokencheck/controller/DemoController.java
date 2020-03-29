package com.github.carlosasrc.pocs.messengerplatform.accesstokencheck.controller;

import com.github.carlosasrc.pocs.messengerplatform.accesstokencheck.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    FacebookService facebookService;

    @GetMapping("/createFacebookAuthorization")
    public String createFacebookAuthorization() {
        return facebookService.createFacebookAuthorizationURL();
    }

    @GetMapping("/facebook")
    public void createFacebookAccessToken(@RequestParam("code") String code){
        facebookService.createFacebookAccessToken(code);
    }

    @GetMapping("/getName")
    public String getNameResponse(){
        return facebookService.getName();
    }

    @GetMapping("/teste")
    public ResponseEntity<String> teste(){
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

}