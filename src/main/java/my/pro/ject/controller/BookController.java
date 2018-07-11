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

    @RequestMapping(value = "/posts/new", method = RequestMethod.GET)
    public String newPost(Model model) {
        model.addAttribute("book", new Book());
        return "new";
    }

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

}
