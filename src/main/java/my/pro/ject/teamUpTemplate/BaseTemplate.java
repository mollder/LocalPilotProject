package my.pro.ject.teamUpTemplate;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class BaseTemplate {
    RestTemplate restTemplate = new RestTemplate();

    protected <T> ResponseEntity<T> get(String url, ParameterizedTypeReference<T> p, OAuth2AccessToken oAuth2AccessToken) {
        return send(url, null, HttpMethod.GET, p, oAuth2AccessToken);
    }

    protected <T> ResponseEntity<T> put(String url, Object request, ParameterizedTypeReference<T> p, OAuth2AccessToken oAuth2AccessToken) {
        return send(url, request, HttpMethod.PUT, p, oAuth2AccessToken);
    }

    protected <T> ResponseEntity<T> post(String url, Object request, ParameterizedTypeReference<T> p, OAuth2AccessToken oAuth2AccessToken) {
        return send(url, request, HttpMethod.POST, p, oAuth2AccessToken);
    }

    private <T> ResponseEntity<T> send(String url, Object request, HttpMethod httpMethod, ParameterizedTypeReference<T> p, OAuth2AccessToken oAuth2AccessToken) {
        HttpHeaders httpHeaders = getHeader(oAuth2AccessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<>(request, httpHeaders);

        return restTemplate.exchange(UriComponentsBuilder.fromUriString(url).toUriString(), httpMethod, httpEntity, p);
    }

    private HttpHeaders getHeader(OAuth2AccessToken oAuth2AccessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.set("Authorization", oAuth2AccessToken.getTokenType()+" "+oAuth2AccessToken.getValue());

        return httpHeaders;
    }
}