package my.pro.ject.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageBook {
    private String bookId;
    private String bookName;
    private boolean isBorrow;
    private String memberName;
    private LocalDate borrowDate;
}
