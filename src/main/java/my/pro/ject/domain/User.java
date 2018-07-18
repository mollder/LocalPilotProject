package my.pro.ject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    private int userIdx;
    @NotNull
    @Id
    private int userId;
    @NotNull
    private String userPassword;
    @NotNull
    private String userName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String token;
}
