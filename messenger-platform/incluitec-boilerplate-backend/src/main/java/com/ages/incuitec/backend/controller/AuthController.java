package com.ages.incuitec.backend.controller;

import com.ages.incuitec.backend.service.FacebookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final FacebookService facebookService;

    public AuthController(FacebookService facebookService) {
        this.facebookService = facebookService;
    }

    @GetMapping("/buscarUrlAutorizacaoMessenger")
    public String getAuthorizationUrl() {
        return facebookService.createFacebookAuthorizationURL();
    }

    @GetMapping("/facebook")
    public void createFacebookAccessToken(@RequestParam("codigo") String code){
        facebookService.createFacebookAccessToken(code);
    }

    @GetMapping("/buscarTokenAcessoPagina")
    public String getPageAccessToken(){
        return facebookService.getPageAccessToken();
    }

}