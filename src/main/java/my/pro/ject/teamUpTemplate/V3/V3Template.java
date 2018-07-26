package my.pro.ject.teamUpTemplate.V3;

import lombok.RequiredArgsConstructor;
import my.pro.ject.domain.Member;
import my.pro.ject.pojo.v3.Say;
import my.pro.ject.pojo.v3.V3Room;
import my.pro.ject.pojo.v3.RoomUsers;
import my.pro.ject.properties.TeamUpProperties;
import my.pro.ject.teamUpTemplate.BaseTemplate;
import my.pro.ject.teamUpTemplate.bot.BotTokenManager;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Component
public class V3Template extends BaseTemplate {
    @NotNull
    private final BotTokenManager botTokenManager;
    @NotNull
    private final TeamUpProperties teamUpProperties;


    public ResponseEntity<V3Room> createRoom(int botTeamNum, Member member) {
        String url = teamUpProperties.getV3Url() + "room/" + botTeamNum; // 봇의 팀 넘버
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        Number[] teams = new Number[]{member.getTeamUpIdx()};
        Object inviteUser = RoomUsers.create(teams);

        ParameterizedTypeReference<V3Room> p = new ParameterizedTypeReference<V3Room>() {
        };

        return post(builder.toUriString(), inviteUser, p, botTokenManager.getBotToken());
    }

    public ResponseEntity<Object> sendMessage(int roomNum, String message) {
        String url = teamUpProperties.getV3Url() + "message/" + roomNum;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        Say say = Say.create(message);

        ParameterizedTypeReference<Object> p = new ParameterizedTypeReference<Object>() {
        };

        return post(builder.toUriString(), say, p, botTokenManager.getBotToken());
    }
}
