package my.pro.ject.teamUpTemplate.V1;

import lombok.RequiredArgsConstructor;
import my.pro.ject.domain.Member;
import my.pro.ject.pojo.v1.MemberGetV1;
import my.pro.ject.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;

@RequiredArgsConstructor
@Component
public class UserInfoManager {

    @NotNull
    private final MemberService memberService;
    @NotNull
    private final V1Template v1Template;

    public Member updateLoginMemberInformation(String memberId, HttpSession httpSession) {
        Member member = memberService.findMember(memberId);

        if(member == null) {
            OAuth2AccessToken oAuth2AccessToken = (OAuth2AccessToken) httpSession.getAttribute("token");
            ResponseEntity<MemberGetV1> memberResponse = v1Template.getUserHttpCommunication(oAuth2AccessToken);

            LinkedHashMap<String, Integer> linkedHashMap = (LinkedHashMap<String, Integer>) memberResponse.getBody().getTeams()[0];
            MemberGetV1 memberGetV1 = memberResponse.getBody();
            member = new Member(memberGetV1.getIndex(),  memberId, memberGetV1.getName(),
                    memberGetV1.getEmail(), linkedHashMap.get("index"));
        }
        memberService.saveMember(member);

        return member;
    }
}
