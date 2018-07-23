package my.pro.ject.teamUpTemplate.bot;

import lombok.RequiredArgsConstructor;
import my.pro.ject.domain.Member;
import my.pro.ject.pojo.v1.MemberGetV1;
import my.pro.ject.pojo.v3.V3Room;
import my.pro.ject.teamUpTemplate.V1.V1Template;
import my.pro.ject.teamUpTemplate.V3.V3Template;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;

@RequiredArgsConstructor
@Component
public class BotAlarmManager {

    @NotNull
    private final V3Template v3Template;
    @NotNull
    private final V1Template v1Template;
    @NotNull
    private final BotTokenManager botTokenManager;

    public void loginAlarm(Member member) {
        ResponseEntity<V3Room> roomResObjResponseEntity = v3Template.createRoom(getTeamNum(), member);
        int roomNum = (int) roomResObjResponseEntity.getBody().getRoom();

        v3Template.sendMessage(roomNum);
    }

    private int getTeamNum() {
        ResponseEntity<MemberGetV1> memberResponse = v1Template.getUserHttpCommunication(botTokenManager.getToken());

        LinkedHashMap<String, Integer> linkedHashMap = (LinkedHashMap<String, Integer>) memberResponse.getBody().getTeams()[0];
        System.out.println();
        return linkedHashMap.get("index");
    }
}
