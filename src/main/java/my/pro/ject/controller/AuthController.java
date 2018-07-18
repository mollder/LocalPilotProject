package my.pro.ject.controller;

import my.pro.ject.dto.UserAuthDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("")
public class AuthController {

    @RequestMapping("teamUpAuth")
    public String auth() {
        return "teamUpTest";
    }

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    @ResponseBody
    public String sendUserInformation(@RequestBody UserAuthDto userAuthDto) {
        System.out.println(userAuthDto.getUserId() + " " + userAuthDto.getUserPassword());

        RestTemplate restTemplate = new RestTemplate();

        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("auth.tmup.com")
                .path("/oauth2/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", "gg4qe2ay3kfj6o2gu888jqu2ldcl5bny")
                .queryParam("redirect_uri", "192.168.183.200:8082/myproject/auth")
                .build()
                .encode()
                .toUri();

        String responseStr = restTemplate.getForObject(uri, String.class);

        System.out.println("ㅇㅇ");

        System.out.println(responseStr);

        userAuthDto.setUserId("ss");
        userAuthDto.setUserPassword("sss");
        return responseStr;
    }
}
