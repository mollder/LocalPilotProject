package my.pro.ject.controller;

import lombok.RequiredArgsConstructor;
import my.pro.ject.domain.Member;
import my.pro.ject.pojo.AddBookReqObj;
import my.pro.ject.service.BookService;
import my.pro.ject.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import my.pro.ject.domain.Book;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class BookController {
    @NotNull
    private final BookService bookService;
    @NotNull
    private final MemberService memberService;

    @RequestMapping(value = "index")
    public String newPost(Model model) {
        model.addAttribute("message", "false");
        return "index";
    }

    @RequestMapping(value = "/test")
    public String testVue() {
        return "test";
    }

    @RequestMapping(value ="book", method = RequestMethod.POST)
    public String addBook(@Valid AddBookReqObj addBookReqObj) {
        boolean result = bookService.addBook(addBookReqObj);
        return "index";
    }

    @RequestMapping(value = "book", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> findBookList() {
        List<Book> bookList = bookService.findBookList();

        return bookList;
    }

    @RequestMapping(value = "book", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseBody
    public Book borrowOrReturnBook(@RequestBody Book book, Principal principal) {
        Member member = memberService.findMember(principal.getName());
        Book result = bookService.borrowOrReturnBook(book, member);
        return result;
    }

    @RequestMapping(value = "bookNum", method = RequestMethod.GET)
    @ResponseBody
    public long findBookNum() {
        return bookService.findBookCount();
    }
}
