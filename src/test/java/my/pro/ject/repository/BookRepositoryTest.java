package my.pro.ject.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Before
    public void setUp() {
        book1 = new Book(1, "Zum 2017 0055", "RxJava Essentials", new Date());
        book2 = new Book(2, "Zum 2017 0095", "모던 자바 웹 UI 바딘 프레임워크", new Date());
        book3 = new Book(3, "zum2015 091002", "자바스크립트 핵심 가이드", new Date());

        bookRepository.deleteAll();
    }

    @After
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Test
    public void addNewBookTest() {
        bookRepository.save(book1);
        assertThat((int)bookRepository.count(), is(1));
    }

    @Test
    public void findAllBookTest() {
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList.size(), is(0));

        bookRepository.save(book1);
        bookList = bookRepository.findAll();
        assertThat(bookList.size(), is(1));

        bookRepository.save(book2);
        bookList = bookRepository.findAll();
        assertThat(bookList.size(), is(2));

        bookRepository.save(book3);
        bookList = bookRepository.findAll();
        assertThat(bookList.size(), is(3));

        bookRepository.deleteAll();
        bookList = bookRepository.findAll();
        assertThat(bookList.size(), is(0));
    }
}
