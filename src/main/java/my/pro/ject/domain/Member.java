package my.pro.ject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
    @Id
    private String memberId;
    private String memberName;
    private int teamUpIdx;
    private String email;
    private Number teamNum;
}
