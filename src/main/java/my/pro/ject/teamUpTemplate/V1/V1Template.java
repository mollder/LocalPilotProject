package my.pro.ject.teamUpTemplate.V1;

import my.pro.ject.pojo.v1.MemberGetV1;
import my.pro.ject.teamUpTemplate.BaseTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class V1Template extends BaseTemplate {

    private String scheme = "https";
    private String host = "auth.tmup.com";

    public ResponseEntity<MemberGetV1> getUserHttpCommunication(OAuth2AccessToken oAuth2AccessToken) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .path("/v1/user");
        ParameterizedTypeReference<MemberGetV1> p = new ParameterizedTypeReference<MemberGetV1>() {
        };

        return get(builder.toUriString(), p , oAuth2AccessToken);
    }
}
