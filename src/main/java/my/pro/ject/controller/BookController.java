package my.pro.ject.controller;

import my.pro.ject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import my.pro.ject.domain.Book;

@Controller
@RequestMapping("")
public class BookController {

    @Autowired
    private BookService bookServiceImpl;

    @RequestMapping(value = "index")
    public String newPost() {
        return "index";
    }

    @RequestMapping(value = "/test")
    public String testVue() {
        return "test";
    }

    @RequestMapping(value ="book", method = RequestMethod.POST)
    public String addBook(Book book, Model model) {
        boolean result = bookServiceImpl.addBook(book);
        model.addAttribute("message", result);
        return "index";
    }
}