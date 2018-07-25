package my.pro.ject.service;

import lombok.RequiredArgsConstructor;
import my.pro.ject.domain.Book;
import my.pro.ject.domain.Member;
import my.pro.ject.repository.BookRepository;
import my.pro.ject.pojo.AddBookReqObj;
import my.pro.ject.teamUpTemplate.bot.BotAlarmManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    @NotNull
    private final BotAlarmManager botAlarmManager;

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
            botAlarmManager.returnAlarm(member, book);

            book.setBorrow(false);
        }
        else {
            botAlarmManager.borrowAlarm(member, book);
            book.setBorrow(true);
        }

        bookRepository.save(book);

        return book;
    }

    public long findBookCount() {
        return bookRepository.count();
    }
}