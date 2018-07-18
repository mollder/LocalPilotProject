package my.pro.ject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Oauth2TokenPostApiResponseDto {
    private String access_token;
    private int expires_int;
    private String token_type;
    private String refresh_token;
}
