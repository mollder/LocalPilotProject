package my.pro.ject.controller;

import my.pro.ject.auth.Login;
import my.pro.ject.dto.UserAuthDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Login login;

    @RequestMapping("")
    public String auth() {
        return "home";
    }

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    @ResponseBody
    public Object sendUserInformation(@RequestBody UserAuthDto userAuthDto, HttpSession httpSession) {
        return login.login(userAuthDto.getUserId(), userAuthDto.getUserPassword(), httpSession);
    }
}