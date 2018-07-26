package my.pro.ject.controller;

import lombok.RequiredArgsConstructor;
import my.pro.ject.domain.Book;
import my.pro.ject.domain.Member;
import my.pro.ject.pojo.PageBook;
import my.pro.ject.service.BookService;
import my.pro.ject.service.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class BookApiController {
    @NotNull
    private final BookService bookService;
    @NotNull
    private final MemberService memberService;

    @RequestMapping(value = "book", method = RequestMethod.GET)
    public List<PageBook> findPageBook(@RequestParam("pageNum") int pageNum) {
        List<PageBook> bookList = bookService.findPageBook(pageNum);

        return bookList;
    }

    @RequestMapping(value = "book", method = RequestMethod.PUT, headers = "Accept=application/json")
    public Book borrowOrReturnBook(@RequestBody Book book, Principal principal) {
        Member member = memberService.findMember(principal.getName());
        Book result = bookService.borrowOrReturnBook(book, member);
        return result;
    }

    @RequestMapping(value = "bookNum", method = RequestMethod.GET)
    public long findBookNum() {
        return bookService.findBookCount();
    }
}