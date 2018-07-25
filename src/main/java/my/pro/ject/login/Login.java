package my.pro.ject.login;

import lombok.RequiredArgsConstructor;
import my.pro.ject.domain.Member;
import my.pro.ject.teamUpTemplate.OAuth2.OAuth2Template;
import my.pro.ject.teamUpTemplate.V1.UserInfoManager;
import my.pro.ject.teamUpTemplate.bot.BotAlarmManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class Login {

    @NotNull
    private final OAuth2Template oAuth2Template;
    @NotNull
    private final UserInfoManager userInfoManager;
    @NotNull
    private final BotAlarmManager botAlarmManager;

    public ResponseEntity<OAuth2AccessToken> login(String memberId ,String memberPassword, HttpSession httpSession) {
        ResponseEntity<OAuth2AccessToken> tokenResponse = oAuth2Template.getToken(memberId, memberPassword);
        OAuth2AccessToken tokenBody = tokenResponse.getBody();

        httpSession.setAttribute("token", tokenBody); // 인증 받은뒤에 세션에 정보를 올리는 과정

        if(tokenBody.getTokenType() != null) {
            Member member = userInfoManager.updateLoginMemberInformation(memberId, httpSession);
            setUserRole(memberId, memberPassword, httpSession);
            botAlarmManager.sendLoginAlarm(member);
        }

        return tokenResponse;
    }

    private void setUserRole(String memberId, String memberPassword, HttpSession httpSession) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(memberId, memberPassword, AuthorityUtils.createAuthorityList("USER")));
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }
}