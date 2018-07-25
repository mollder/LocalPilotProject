package my.pro.ject.service;

import lombok.RequiredArgsConstructor;
import my.pro.ject.domain.Book;
import my.pro.ject.domain.BorrowBook;
import my.pro.ject.domain.Member;
import my.pro.ject.pojo.PageBook;
import my.pro.ject.repository.BookRepository;
import my.pro.ject.pojo.AddBookReq;
import my.pro.ject.repository.BorrowBookRepository;
import my.pro.ject.teamUpTemplate.bot.BotAlarmManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.LinkedList;
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

    public boolean addBook(AddBookReq addBookReq) {
        if(addBookReq != null) {
            Book book = new Book();
            book.setBookId(addBookReq.getId());
            book.setBookName(addBookReq.getName());
            book.setBorrow(false);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    public List<PageBook> findPageBook(int pageNum) {
        PageRequest request = new PageRequest(pageNum-1, 5, Sort.Direction.ASC, "bookId");
        List<Book> bookList = bookRepository.findAllBy(request);

        List<PageBook> list = new LinkedList<>();
        for(Book book : bookList) {
            PageBook pageBook = new PageBook();
            pageBook.setBookId(book.getBookId());
            pageBook.setBookName(book.getBookName());
            pageBook.setBorrow(book.isBorrow());
            pageBook.setMemberName("");

            if(book.isBorrow()) {
                BorrowBook borrowBook = borrowBookRepository.findBorrowBookByBook_BookId(book.getBookId());
                pageBook.setBorrowDate(borrowBook.getBorrowDate());
                pageBook.setMemberName(borrowBook.getMember().getMemberName());
            }
            list.add(pageBook);
        }

        return list;
    }

    public Book borrowOrReturnBook(Book book, Member member) {
        if(book.isBorrow()) {
            BorrowBook borrowBook = new BorrowBook();
            borrowBook.setBook(book);
            borrowBook.setMember(member);
            borrowBookRepository.deleteBorrowBookByBook_BookId(book.getBookId());

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