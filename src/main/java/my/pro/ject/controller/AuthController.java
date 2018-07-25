package my.pro.ject.controller;

import lombok.RequiredArgsConstructor;
import my.pro.ject.login.Login;
import my.pro.ject.pojo.MemberAuthReq;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class AuthController {
    @NotNull
    private final Login login;

    @RequestMapping("")
    public String auth() {
        return "home";
    }

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<OAuth2AccessToken> sendUserInformation(@RequestBody MemberAuthReq memberAuthReq, HttpSession httpSession) {
        return login.login(memberAuthReq.getMemberId(), memberAuthReq.getMemberPassword(), httpSession);
    }
}