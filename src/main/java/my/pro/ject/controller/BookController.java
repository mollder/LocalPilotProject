package my.pro.ject.controller;

import lombok.RequiredArgsConstructor;
import my.pro.ject.pojo.AddBookReq;
import my.pro.ject.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class BookController {
    @NotNull
    private final BookService bookService;

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
    public String addBook(@Valid AddBookReq addBookReq) {
        boolean result = bookService.addBook(addBookReq);
        return "index";
    }
}
