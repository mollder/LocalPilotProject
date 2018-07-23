package my.pro.ject.service;

import lombok.RequiredArgsConstructor;
import my.pro.ject.domain.Book;
import my.pro.ject.domain.Member;
import my.pro.ject.repository.BookRepository;
import my.pro.ject.pojo.AddBookReqObj;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    @NotNull
    private final BookRepository bookRepository;

    public boolean addBook(AddBookReqObj addBookReqObj) {
        if(addBookReqObj != null) {
            Book book = new Book();
            book.setBookId(addBookReqObj.getId());
            book.setBookName(addBookReqObj.getName());
            book.setBorrow(false);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    public List<Book> findBookList() {
        List<Book> bookList = bookRepository.findAllBy();
        return bookList;
    }

    public Book borrowOrReturnBook(Book book, Member member) {
        if(book.isBorrow()) {
            book.setBorrow(false);
        }
        else {
            book.setBorrow(true);
        }

        bookRepository.save(book);

        return book;
    }

    public long findBookCount() {
        return bookRepository.count();
    }
}