package my.pro.ject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Book {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @Id
    private String bookId;

    private String bookName;

    private boolean isBorrow = false;
}
