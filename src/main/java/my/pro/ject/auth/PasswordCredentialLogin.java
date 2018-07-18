package my.pro.ject.auth;

import my.pro.ject.domain.User;
import my.pro.ject.dto.Oauth2TokenPostApiResponseDto;
import my.pro.ject.dto.V1AuthUserGetAPIResponseDto;
import my.pro.ject.http.HttpCommunication;
import my.pro.ject.service.BookService;
import my.pro.ject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class PasswordCredentialLogin implements Login {

    @Autowired
    private HttpCommunication httpCommunication;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @Override
    public Object login(String userId, String userPassword, HttpSession httpSession) {
        ResponseEntity<Oauth2TokenPostApiResponseDto> tokenResponse = httpCommunication.passwordCredentialHttpCommunication(userId, userPassword);

        if(tokenResponse.toString().contains("access_token")) {
            String token = tokenResponse.getBody().getAccess_token();
            String refreshToken = tokenResponse.getBody().getRefresh_token();

            User user = userService.findUser(userId);

            if(user == null) {
                ResponseEntity<V1AuthUserGetAPIResponseDto> userResponse = httpCommunication.getUserHttpCommunication(token);
                V1AuthUserGetAPIResponseDto v1AuthUserGetAPIResponseDto = userResponse.getBody();
                user = new User(v1AuthUserGetAPIResponseDto.getUserIdx(), userId, userPassword, v1AuthUserGetAPIResponseDto.getUserName(), v1AuthUserGetAPIResponseDto.getUserEmail(), token, refreshToken);
            }else {
                user.setToken(token);
                user.setRefreshToken(refreshToken);
            }
            userService.insertUser(user);

            userAuthorize(userId, userPassword, httpSession);
        }

        return tokenResponse;
    }

    private void userAuthorize(String userId, String userPassword, HttpSession httpSession) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, userPassword, AuthorityUtils.createAuthorityList("USER")));
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }
}