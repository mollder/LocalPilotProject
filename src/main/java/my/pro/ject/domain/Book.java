package my.pro.ject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @Column(unique = true)
    @NotNull
    private String bookId;

    @NotNull
    private String bookName;

    @Column(name = "isBorrow")
    private boolean isBorrow = false;

    /*
    public static class Builder {
        private Boolean include = false; // Here it comes your default value

        public Builder include(Boolean include) {
            this.include = include;
            return this;
        }

        public Book build() {
            Book book = new Book ();
            book.setBorrow(include);
            return book;
        }
    }
    */

    private Date borrowDate;
}
