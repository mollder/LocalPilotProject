package my.pro.ject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.pro.ject.pojo.BorrowBookPk;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@IdClass(BorrowBookPk.class)
public class BorrowBook{
    @Id
    @OneToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Id
    @OneToOne
    @JoinColumn(name = "bookId")
    private Book book;

    private LocalDate borrowDate;
}
