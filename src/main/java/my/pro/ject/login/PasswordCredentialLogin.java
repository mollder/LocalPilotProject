package my.pro.ject.login;

import my.pro.ject.domain.Member;
import my.pro.ject.pojo.v1.MemberGetV1;
import my.pro.ject.pojo.v3.V3Room;
import my.pro.ject.teamUpTemplate.BaseTemplate;
import my.pro.ject.service.BookService;
import my.pro.ject.service.MemberService;
import my.pro.ject.teamUpTemplate.OAuth2.OAuth2Template;
import my.pro.ject.teamUpTemplate.V1.V1Template;
import my.pro.ject.teamUpTemplate.V3.V3Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@Component
public class PasswordCredentialLogin {

    @Autowired
    private BaseTemplate baseTemplate;
    @Autowired
    private V1Template v1Template;
    @Autowired
    private V3Template v3Template;
    @Autowired
    private OAuth2Template oAuth2Template;
    @Autowired
    private BookService bookService;
    @Autowired
    private MemberService memberService;

    public ResponseEntity<OAuth2AccessToken> login(String memberId ,String memberPassword, HttpSession httpSession) {
        ResponseEntity<OAuth2AccessToken> tokenResponse = oAuth2Template.passwordCredentialHttpCommunication(memberId, memberPassword);
        OAuth2AccessToken tokenBody = tokenResponse.getBody();

        httpSession.setAttribute("token", tokenBody); // 인증 받은뒤에 세션에 정보를 올리는 과정

        if(tokenBody.getTokenType() != null) {
            Member member = updateLoginMemberInformation(memberId, httpSession);
            userAuthorize(memberId, memberPassword, httpSession);
            ResponseEntity<V3Room> roomResObjResponseEntity = v3Template.createRoom(member, httpSession);
            int roomNum = (int) roomResObjResponseEntity.getBody().getRoom();

            v3Template.sendMessage(httpSession, roomNum);
        }

        return tokenResponse;
    }

    private Member updateLoginMemberInformation(String memberId, HttpSession httpSession) {
        Member member = memberService.findMember(memberId);

        if(member == null) {
            ResponseEntity<MemberGetV1> memberResponse = v1Template.getUserHttpCommunication(httpSession);

            LinkedHashMap<String, Integer> linkedHashMap = (LinkedHashMap<String, Integer>) memberResponse.getBody().getTeams()[0];
            MemberGetV1 memberGetV1 = memberResponse.getBody();
            member = new Member(memberGetV1.getIndex(),  memberId, memberGetV1.getName(),
                    memberGetV1.getEmail(), linkedHashMap.get("index"));
        }
        memberService.saveMember(member);

        return member;
    }

    private void userAuthorize(String memberId, String memberPassword, HttpSession httpSession) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(memberId, memberPassword, AuthorityUtils.createAuthorityList("USER")));
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }
}