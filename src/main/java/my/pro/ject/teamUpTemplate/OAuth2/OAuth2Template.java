package my.pro.ject.teamUpTemplate.OAuth2;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OAuth2Template {
    RestTemplate restTemplate = new RestTemplate();
    String client_id = "gg4qe2ay3kfj6o2gu888jqu2ldcl5bny";
    String client_secret = "omcbx76tvrkpom45";
    String grant_type = "password";

    public ResponseEntity<OAuth2AccessToken> passwordCredentialHttpCommunication(String userId, String userPassword) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("auth.tmup.com")
                .path("/oauth2/token")
                .queryParam("grant_type", grant_type)
                .queryParam("client_id", client_id)
                .queryParam("client_secret", client_secret)
                .queryParam("username", userId)
                .queryParam("password", userPassword);
        HttpEntity entity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, OAuth2AccessToken.class);
    }
}
