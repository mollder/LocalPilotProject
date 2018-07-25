package my.pro.ject.pojo.v3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomUsers {
    @JsonProperty("users")
    Number[] users;

    public static RoomUsers create(Number[] teams){
        RoomUsers c = new RoomUsers();
        c.setUsers(teams);
        return c;
    }
}