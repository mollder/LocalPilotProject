package my.pro.ject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    @Temporal(TemporalType.TIMESTAMP)
    private Date borrowDate;
}
