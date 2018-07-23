package my.pro.ject.teamUpTemplate;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;

@Component
public class BaseTemplate {
    RestTemplate restTemplate = new RestTemplate();

    protected <T> ResponseEntity<T> get(String url, ParameterizedTypeReference<T> p, HttpSession httpSession) {
        return send(url, null, HttpMethod.GET, p, httpSession);
    }

    protected <T> ResponseEntity<T> put(String url, Object request, ParameterizedTypeReference<T> p, HttpSession httpSession) {
        return send(url, request, HttpMethod.PUT, p, httpSession);
    }

    protected <T> ResponseEntity<T> post(String url, Object request, ParameterizedTypeReference<T> p, HttpSession httpSession) {
        return send(url, request, HttpMethod.POST, p, httpSession);
    }

    private HttpHeaders getHerader(HttpSession httpSession) {
        OAuth2AccessToken token = (OAuth2AccessToken) httpSession.getAttribute("token");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.set("Authorization", token.getTokenType()+" "+token.getValue());

        return httpHeaders;
    }

    private <T> ResponseEntity<T> send(String url, Object request, HttpMethod httpMethod, ParameterizedTypeReference<T> p, HttpSession httpSession) {
        HttpHeaders httpHeaders = getHerader(httpSession);

        HttpEntity<Object> httpEntity = new HttpEntity<>(request, httpHeaders);

        return restTemplate.exchange(UriComponentsBuilder.fromUriString(url).toUriString(), httpMethod, httpEntity, p);
    }
}