package my.pro.ject.teamUpTemplate.bot;

import lombok.RequiredArgsConstructor;
import my.pro.ject.teamUpTemplate.OAuth2.OAuth2Template;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class BotTokenManager {
    private String botId = "inguechat@zuminternet.com";
    private String botPassword = "eldrb0116/*";
    OAuth2AccessToken token;

    @NotNull
    private final OAuth2Template oAuth2Template;

    public OAuth2AccessToken getBotToken() {
        if (token == null) {
            ResponseEntity<OAuth2AccessToken> responseEntity = oAuth2Template.getToken(botId, botPassword);
            token = responseEntity.getBody();
        } else if (token.isExpired()) {
            token = oAuth2Template.refreshToken(token.getRefreshToken().getValue()).getBody();
        }

        return this.token;
    }
}
