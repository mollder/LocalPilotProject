package my.pro.ject.service;

import my.pro.ject.domain.Book;
import java.util.List;


public interface BookService {
    List<Book> seeAllBookList();
    boolean addBook(Book book);
}
