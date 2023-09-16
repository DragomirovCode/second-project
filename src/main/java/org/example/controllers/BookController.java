package org.example.controllers;

import org.example.dao.BookDAO;
import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String showAllBooks(Model model){
        model.addAttribute("books", bookDAO.getBooks());
        return "books/show";
    }

    @GetMapping("/new")
    public String showFormForNewBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String processNewBook(@ModelAttribute("book") Book book){
        bookDAO.addBook(book);
        return "redirect:/books/";
    }

    @GetMapping("/{id}")
    public String processFetchPersonById(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.getBookById(id));
        return "books/id";
    }

    @GetMapping("/{id}/edit")
    public String showFormForEdit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String processEditForm(@PathVariable("id") int id, @ModelAttribute("book") Book book){
        bookDAO.updateBook(id, book);
        return "redirect:/books/";
    }

    @DeleteMapping("/{id}")
    public String processDelete(@PathVariable("id") int id){
        bookDAO.deleteBook(id);
        return "redirect:/books/";
    }
}