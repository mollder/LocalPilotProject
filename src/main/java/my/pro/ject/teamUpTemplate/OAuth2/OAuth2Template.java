package my.pro.ject.teamUpTemplate.OAuth2;

import lombok.RequiredArgsConstructor;
import my.pro.ject.properties.TeamUpProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class OAuth2Template {

    @NotNull
    private final TeamUpProperties teamUpProperties;

    RestTemplate restTemplate = new RestTemplate();

    String grant_type = "password";
    String scheme = "https";
    String host = "auth.tmup.com";

    public ResponseEntity<OAuth2AccessToken> getToken(String userId, String userPassword) {
        HttpHeaders httpHeaders = getHeader();

        UriComponentsBuilder builder = getUri()
                .queryParam("grant_type", grant_type)
                .queryParam("client_id", teamUpProperties.getClientId())
                .queryParam("client_secret", teamUpProperties.getClientSecret())
                .queryParam("username", userId)
                .queryParam("password", userPassword);
        HttpEntity entity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, OAuth2AccessToken.class);
    }

    public ResponseEntity<OAuth2AccessToken> refreshToken(String refreshToken) {
        HttpHeaders httpHeaders = getHeader();

        UriComponentsBuilder builder = getUri()
                .queryParam("grant_type", grant_type)
                .queryParam("refresh_token", refreshToken);
        HttpEntity entity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, OAuth2AccessToken.class);
    }

    private UriComponentsBuilder getUri() {
        return UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .path("/oauth2/token");
    }

    public HttpHeaders getHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

        return httpHeaders;
    }
}
