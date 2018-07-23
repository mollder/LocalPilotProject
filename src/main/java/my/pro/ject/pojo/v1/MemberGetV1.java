package my.pro.ject.pojo.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberGetV1 {
    private int index;
    private String name;
    private int teamUpIdx;
    private String email;
    private Object[] teams;
}
