package my.pro.ject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembAuthReqObj {
    private String memberId;
    private String memberPassword;
}
