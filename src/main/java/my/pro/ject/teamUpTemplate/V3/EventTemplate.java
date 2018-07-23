package my.pro.ject.teamUpTemplate.V3;

import lombok.RequiredArgsConstructor;
import my.pro.ject.teamUpTemplate.BaseTemplate;
import my.pro.ject.teamUpTemplate.bot.BotTokenManager;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Component
public class EventTemplate extends BaseTemplate {
    private String url = "https://ev.tmup.com/v3/";
    @NotNull
    private final BotTokenManager botTokenManager;

    public ResponseEntity<Object> event(HttpSession httpSession) {
        String url = this.url+"events";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        ParameterizedTypeReference<Object> p = new ParameterizedTypeReference<Object>() {
        };

        return get(builder.toUriString(), p, botTokenManager.getToken());
    }
}
