package my.pro.ject.service;

import lombok.RequiredArgsConstructor;
import my.pro.ject.domain.Book;
import my.pro.ject.domain.BorrowBook;
import my.pro.ject.domain.Member;
import my.pro.ject.repository.BookRepository;
import my.pro.ject.pojo.AddBookReqObj;
import my.pro.ject.repository.BorrowBookRepository;
import my.pro.ject.teamUpTemplate.bot.BotAlarmManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    @NotNull
    private final BookRepository bookRepository;
    @NotNull
    private final BotAlarmManager botAlarmManager;
    @NotNull
    private final BorrowBookRepository borrowBookRepository;

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

    public List<Book> findPageBook(int pageNum) {
        PageRequest request = new PageRequest(pageNum-1, 5, Sort.Direction.ASC, "idx");
        List<Book> bookList = bookRepository.findAllBy(request);

        return bookList;
    }

    public Book borrowOrReturnBook(Book book, Member member) {
        if(book.isBorrow()) {
            BorrowBook borrowBook = new BorrowBook();
            borrowBook.setBook(book);
            borrowBook.setMember(member);
            borrowBookRepository.delete(borrowBook);

            botAlarmManager.sendReturnAlarm(member, book);

            book.setBorrow(false);
        }
        else {
            BorrowBook borrowBook = new BorrowBook();
            borrowBook.setMember(member);
            borrowBook.setBook(book);
            borrowBook.setBorrowDate(LocalDate.now());

            borrowBookRepository.save(borrowBook);

            botAlarmManager.sendBorrowAlarm(borrowBook);
            book.setBorrow(true);
        }

        bookRepository.save(book);

        return book;
    }

    public long findBookCount() {
        return bookRepository.count();
    }
}