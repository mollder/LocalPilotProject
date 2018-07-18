package my.pro.ject.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class HttpCommunication {

    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> passwordCredentialHttpCommunication(String userId, String userPassword) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("auth.tmup.com")
                .path("/oauth2/token")
                .queryParam("grant_type", "password")
                .queryParam("client_id", "gg4qe2ay3kfj6o2gu888jqu2ldcl5bny")
                .queryParam("client_secret", "omcbx76tvrkpom45")
                .queryParam("username", userId)
                .queryParam("password", userPassword);
        HttpEntity entity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
    }
}
