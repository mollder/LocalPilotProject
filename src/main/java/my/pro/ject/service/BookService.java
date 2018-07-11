package my.pro.ject.service;

import org.springframework.stereotype.Service;
import my.pro.ject.domain.Book;

import java.util.List;


public interface BookService {
    public List<Book> seeAllBookList();
}
