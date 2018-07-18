package my.pro.ject.http;

import my.pro.ject.dto.Oauth2TokenPostApiResponseDto;
import my.pro.ject.dto.V1AuthUserGetAPIResponseDto;
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

    String client_id = "gg4qe2ay3kfj6o2gu888jqu2ldcl5bny";
    String client_secret = "omcbx76tvrkpom45";

    public ResponseEntity<Oauth2TokenPostApiResponseDto> passwordCredentialHttpCommunication(String userId, String userPassword) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("auth.tmup.com")
                .path("/oauth2/token")
                .queryParam("grant_type", "password")
                .queryParam("client_id", client_id)
                .queryParam("client_secret", client_secret)
                .queryParam("username", userId)
                .queryParam("password", userPassword);
        HttpEntity entity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, Oauth2TokenPostApiResponseDto.class);
    }

    public ResponseEntity<V1AuthUserGetAPIResponseDto> getUserHttpCommunication(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("auth.tmup.com")
                .path("/v1/user");
        HttpEntity entity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, V1AuthUserGetAPIResponseDto.class);
    }
}
