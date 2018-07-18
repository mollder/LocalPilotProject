package my.pro.ject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class V1AuthUserGetAPIResponseDto {
    private int userIdx;
    private String userName;
    private String userEmail;
}
