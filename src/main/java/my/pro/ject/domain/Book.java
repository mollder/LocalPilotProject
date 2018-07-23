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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    private String bookId;

    private String bookName;

    private boolean isBorrow = false;

    public void setBookId(String id) {
        this.bookId = id;
    }

    public void setBookName(String name) {
        this.bookName = name;
    }

    public void setBorrow(boolean b) {
        this.isBorrow = b;
    }

    public boolean isBorrow() {
        return this.isBorrow;
    }
}
