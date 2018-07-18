package my.pro.ject.auth;

import my.pro.ject.domain.User;
import my.pro.ject.dto.V1AuthUserGetAPIResponseDto;
import my.pro.ject.http.HttpCommunication;
import my.pro.ject.service.BookService;
import my.pro.ject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
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
        ResponseEntity<OAuth2AccessToken> tokenResponse = httpCommunication.passwordCredentialHttpCommunication(userId, userPassword);

        if(tokenResponse.getBody().getTokenType().equals("bearer")) {
            String token = tokenResponse.getBody().getValue();
            String refreshToken = tokenResponse.getBody().getRefreshToken().getValue();
            String tokenType = tokenResponse.getBody().getTokenType();

            User user = userService.findUser(userId);

            if(user == null) {
                ResponseEntity<V1AuthUserGetAPIResponseDto> userResponse = httpCommunication.getUserHttpCommunication(tokenResponse.getBody());
                V1AuthUserGetAPIResponseDto v1AuthUserGetAPIResponseDto = userResponse.getBody();
                user = new User(v1AuthUserGetAPIResponseDto.getIndex(), userId, userPassword, v1AuthUserGetAPIResponseDto.getName(), v1AuthUserGetAPIResponseDto.getEmail(), token, refreshToken);
            }else {
                user.setToken(token);
                user.setRefreshToken(refreshToken);
            }

            userService.insertUser(user);
        }
        userAuthorize(userId, userPassword, httpSession);

        return tokenResponse;
    }

    private void userAuthorize(String userId, String userPassword, HttpSession httpSession) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, userPassword, AuthorityUtils.createAuthorityList("USER")));
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }
}