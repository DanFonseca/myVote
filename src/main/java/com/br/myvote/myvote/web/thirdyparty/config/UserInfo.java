package com.br.myvote.myvote.web.thirdyparty.config;

import com.br.myvote.myvote.business.response.UserInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class UserInfo   {

    private final RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(UserInfo.class);

    @Value("${myVote.base-uri.user-info}")
    private String baseUri;

    public UserInfo (RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public boolean validCPF (String cpf) {
        String uri = baseUri + cpf;

        try{
            UserInfoResponse userInfoResponse = restTemplate.getForObject(uri, UserInfoResponse.class);
            assert userInfoResponse != null;
            return doValid(userInfoResponse);
        }catch (HttpStatusCodeException e ){
            logger.error("uri: {} error message: {}",  uri, e.getMessage());
            logger.info("Returning default User Info Response");
            return true;
        }
    }

    public boolean doValid (UserInfoResponse userInfoResponse) {
        return userInfoResponse.status().equals("ABLE_TO_VOTE");
    }
}
