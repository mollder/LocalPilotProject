package my.pro.ject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class BorrowBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @OneToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToOne
    @JoinColumn(name = "bookId")
    private Book book;

    private LocalDate borrowDate;
}
