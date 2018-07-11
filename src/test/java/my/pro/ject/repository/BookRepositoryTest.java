package my.pro.ject.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import my.pro.ject.domain.Book;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    Book book1;

    @Before
    public void setUp() {
        book1 = new Book(1, "Zum 2017 0055", "RxJava Essentials", new Date());
    }

    @Test
    public void addNewBook() {
        bookRepository.save(book1);
        assertThat((int)bookRepository.count(), is(1));
        bookRepository.deleteAll();
    }
}
