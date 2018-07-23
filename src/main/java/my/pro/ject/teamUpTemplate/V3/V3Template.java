package my.pro.ject.teamUpTemplate.V3;

import my.pro.ject.domain.Member;
import my.pro.ject.pojo.v3.Say;
import my.pro.ject.pojo.v3.V3Room;
import my.pro.ject.pojo.v3.roomCreate;
import my.pro.ject.teamUpTemplate.BaseTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;

@Component
public class V3Template extends BaseTemplate {
    private String url = "https://edge.tmup.com/v3/";

    public ResponseEntity<V3Room> createRoom(Member member, HttpSession httpSession) {
        String url = this.url+"room/" + member.getTeamNum();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        Number[] teams = new Number[1];
        teams[0] = 10849;// 울트론 번호
        Object inviteUser = roomCreate.create(teams);

        ParameterizedTypeReference<V3Room> p = new ParameterizedTypeReference<V3Room>() {
        };

        return post(builder.toUriString(), inviteUser, p, httpSession);
    }

    public ResponseEntity<Object> sendMessage(HttpSession httpSession, int roomNum) {
        String url = this.url+"message/"+roomNum;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        Say say = Say.create("로그인 하셨습니다.");

        ParameterizedTypeReference<Object> p = new ParameterizedTypeReference<Object>() {
        };

        return post(builder.toUriString(), say, p, httpSession);
    }
}
