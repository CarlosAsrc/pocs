package com.ages.incuitec.backend.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:application.properties")
public class FacebookService {
    @Value("${spring.social.facebook.appId}")
    String facebookAppId;
    @Value("${spring.social.facebook.appSecret}")
    String facebookSecret;

    @Autowired
    private RestTemplate restTemplate;

    private String userAccessToken;
    private String userId;
    private static final Logger log = LogManager.getLogger(FacebookService.class);


    public String createFacebookAuthorizationURL(){
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:8080/facebook");
        params.setScope("public_profile,email,user_birthday,manage_pages,pages_show_list");
        return oauthOperations.buildAuthorizeUrl(params);
    }

    public void createFacebookAccessToken(String code) {
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
        AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, "http://localhost:8080/facebook", null);
        userAccessToken = accessGrant.getAccessToken();

        Facebook facebook = new FacebookTemplate(userAccessToken);
        String[] fields = {"id", "name"};
        User user = facebook.fetchObject("me", User.class, fields);
        userId = user.getId();
    }


    public String getPageAccessToken() {
        return restTemplate.getForObject("https://graph.facebook.com/"+ userId +"/accounts?access_token="+ userAccessToken,  String.class);
    }

    public String checkPageAccessToken() {
        return restTemplate.getForObject("https://graph.facebook.com/"+ userId +"/accounts?access_token="+ userAccessToken,  String.class);
    }
}
