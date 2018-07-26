package my.pro.ject.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import my.pro.ject.domain.Book;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    Book book1;
    Book book2;
    Book book3;
    PageRequest pageRequest;

    @Before
    public void setUp() {
        book1 = new Book("Zum 2017 0055", "RxJava Essentials", false);
        book2 = new Book("Zum 2017 0095", "모던 자바 웹 UI 바딘 프레임워크",false);
        book3 = new Book("zum2015 091002", "자바스크립트 핵심 가이드", false);

        pageRequest = new PageRequest(1, 5, Sort.Direction.ASC, "bookId");

        bookRepository.deleteAll();
    }

    @After
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Test()
    public void updateTest() {
        bookRepository.save(book1);

        book1.setBorrow(true);

        bookRepository.save(book1);
    }

    @Test
    public void addNewBookTest() {
        bookRepository.save(book1);
        assertThat((int)bookRepository.count(), is(1));
    }

    @Test
    public void findAllBookTest() {
        List<Book> bookList = bookRepository.findAllBy(pageRequest);
        assertThat(bookList.size(), is(0));

        bookRepository.save(book1);
        bookList = bookRepository.findAllBy(pageRequest);
        assertThat(bookList.size(), is(1));

        bookRepository.save(book2);
        bookList = bookRepository.findAllBy(pageRequest);
        assertThat(bookList.size(), is(2));

        bookRepository.save(book3);
        bookList = bookRepository.findAllBy(pageRequest);
        assertThat(bookList.size(), is(3));

        bookRepository.deleteAll();
        bookList = bookRepository.findAllBy(pageRequest);
        assertThat(bookList.size(), is(0));
    }
}
