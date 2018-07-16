package my.pro.ject.service;

import my.pro.ject.domain.Book;
import my.pro.ject.repository.BookRepository;
import my.pro.ject.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    public boolean addBook(BookDto bookDto) {
        if(bookDto != null) {
            Book book = new Book();
            book.setBookId(bookDto.getId());
            book.setBookName(bookDto.getName());
            book.setBorrow(false);
            book.setBorrowDate(null);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    @Override
    public List<Book> findBookList() {
        return bookRepository.findAllBy();
    }
}