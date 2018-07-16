package my.pro.ject.controller;

import my.pro.ject.dto.BookDto;
import my.pro.ject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import my.pro.ject.domain.Book;

import java.util.List;

@Controller
@RequestMapping("")
public class BookController {
    @Autowired
    private BookService bookServiceImpl;

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
    public String addBook(BookDto bookDto, String name, Model model) {
        boolean result = bookServiceImpl.addBook(bookDto);
        return "index";
    }

    @RequestMapping(value = "book", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> findBookList() {
        List<Book> bookList = bookServiceImpl.findBookList();

        return bookList;
    }
}
