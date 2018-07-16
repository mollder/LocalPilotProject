package my.pro.ject.service;

import my.pro.ject.domain.Book;
import my.pro.ject.dto.BookDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BookService {
    boolean addBook(BookDto bookDTO);

    List<Book> findBookList();
}
