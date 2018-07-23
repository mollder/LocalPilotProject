package my.pro.ject.teamUpTemplate.V3;

import lombok.RequiredArgsConstructor;
import my.pro.ject.domain.Member;
import my.pro.ject.pojo.v3.Say;
import my.pro.ject.pojo.v3.V3Room;
import my.pro.ject.pojo.v3.roomCreate;
import my.pro.ject.teamUpTemplate.BaseTemplate;
import my.pro.ject.teamUpTemplate.bot.BotAlarmManager;
import my.pro.ject.teamUpTemplate.bot.BotTokenManager;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Component
public class V3Template extends BaseTemplate {
    @NotNull
    private final BotTokenManager botTokenManager;

    private String url = "https://edge.tmup.com/v3/";

    public ResponseEntity<V3Room> createRoom(int botTeamNum, Member member) {
        String url = this.url+"room/" + botTeamNum;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        Number[] teams = new Number[1];
        teams[0] = member.getMemberIdx();// 내 번호
        Object inviteUser = roomCreate.create(teams);

        ParameterizedTypeReference<V3Room> p = new ParameterizedTypeReference<V3Room>() {
        };

        return post(builder.toUriString(), inviteUser, p, botTokenManager.getToken());
    }

    public ResponseEntity<Object> sendMessage(int roomNum) {
        String url = this.url+"message/"+roomNum;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        Say say = Say.create("도서 대여 시스템에 로그인되셨습니다.");

        ParameterizedTypeReference<Object> p = new ParameterizedTypeReference<Object>() {
        };

        return post(builder.toUriString(), say, p, botTokenManager.getToken());
    }
}
