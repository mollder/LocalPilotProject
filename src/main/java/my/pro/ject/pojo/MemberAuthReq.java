package my.pro.ject.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberAuthReq {
    private String memberId;
    private String memberPassword;
}
