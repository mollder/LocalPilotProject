package my.pro.ject.teamUpTemplate.V3;

import my.pro.ject.teamUpTemplate.BaseTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;

public class EventTemplate extends BaseTemplate {
    private String url = "https://ev.tmup.com/v3/";

    public ResponseEntity<Object> event(HttpSession httpSession) {
        String url = this.url+"events";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        ParameterizedTypeReference<Object> p = new ParameterizedTypeReference<Object>() {
        };

        return get(builder.toUriString(), p, httpSession);
    }
}
