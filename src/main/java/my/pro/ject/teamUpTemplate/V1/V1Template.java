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

    private String url = "https://auth.tmup.com/v1/";

    public ResponseEntity<MemberGetV1> getUserHttpCommunication(OAuth2AccessToken oAuth2AccessToken) {
        String url = this.url + "user/"; // 봇의 팀 넘버
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        ParameterizedTypeReference<MemberGetV1> p = new ParameterizedTypeReference<MemberGetV1>() {
        };

        return get(builder.toUriString(), p,  oAuth2AccessToken);
    }
}
