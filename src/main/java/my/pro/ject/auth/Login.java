package my.pro.ject.auth;

import javax.servlet.http.HttpSession;

public interface Login {

    Object login(String userId, String userPassword, HttpSession httpSession);
}
