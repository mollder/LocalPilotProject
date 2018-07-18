package my.pro.ject.auth;

import my.pro.ject.http.HttpCommunication;
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

    @Override
    public Object login(String userId, String userPassword, HttpSession httpSession) {
        ResponseEntity<String> response = httpCommunication.passwordCredentialHttpCommunication(userId, userPassword);

        if(response.toString().contains("access_token")) userAuthorize(userId, userPassword, httpSession);

        return response;
    }

    private void userAuthorize(String userId, String userPassword, HttpSession httpSession) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, userPassword, AuthorityUtils.createAuthorityList("USER")));
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }
}