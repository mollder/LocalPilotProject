package my.pro.ject.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.pro.ject.domain.Book;
import my.pro.ject.domain.Member;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowBookPk implements Serializable {
    private Member member;
    private Book book;
}
