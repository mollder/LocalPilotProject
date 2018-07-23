package my.pro.ject.controller;

import my.pro.ject.login.PasswordCredentialLogin;
import my.pro.ject.pojo.MembAuthReqObj;
import my.pro.ject.teamUpTemplate.BaseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class AuthController {

    @Autowired
    private PasswordCredentialLogin login;
    @Autowired
    private BaseTemplate baseTemplate;

    @RequestMapping("")
    public String auth() {
        return "home";
    }

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<OAuth2AccessToken> sendUserInformation(@RequestBody MembAuthReqObj membAuthReqObj, HttpSession httpSession) {
        return login.login(membAuthReqObj.getMemberId(), membAuthReqObj.getMemberPassword(), httpSession);
    }
}