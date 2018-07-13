package my.pro.ject.service;

import my.pro.ject.domain.Book;
import my.pro.ject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    public List<Book> seeAllBookList() {
        return bookRepository.findAll();
    }

    public boolean addBook(Book book) {
        if(book != null) {
            bookRepository.save(book);
            return true;
        }
        return false;
    }
}