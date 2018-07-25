package my.pro.ject.pojo.v1;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberGetV1 {
    private int index;
    private String name;
    private int teamUpIdx;
    private String email;
    private Object[] teams;
}