package my.pro.ject.controller;

import lombok.RequiredArgsConstructor;
import my.pro.ject.login.Login;
import my.pro.ject.pojo.MemberAuthReq;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class AuthController {
    @NotNull
    private final Login login;

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public ResponseEntity<OAuth2AccessToken> sendUserInformation(@RequestBody MemberAuthReq memberAuthReq, HttpSession httpSession) {
        return login.login(memberAuthReq.getMemberId(), memberAuthReq.getMemberPassword(), httpSession);
    }
}