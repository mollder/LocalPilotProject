package my.pro.ject.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class TeamUpProperties {
    @Value("${teamup.client.id }")
    private String clientId;

    @Value("${teamup.client.secret}")
    private String clientSecret;

    @Value("${teamup.bot.id}")
    private String botId;

    @Value("${teamup.bot.pw}")
    private String botPassword;
}