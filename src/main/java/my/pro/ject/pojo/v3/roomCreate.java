package my.pro.ject.pojo.v3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class roomCreate {
    @JsonProperty("users")
    Number[] users;

    public static roomCreate create(Number[] teams){
        roomCreate c = new roomCreate();
        c.setUsers(teams);
        return c;
    }
}