package org.example.controllers;

import org.example.dao.BookDAO;
import org.example.dao.PersonDAO;
import org.example.model.Book;
import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;

    private final PersonDAO personDAO;


    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        return "books/show";
    }

    @GetMapping("/new")
    public String showFormForNewBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String processNewBook(@ModelAttribute("book") @Valid Book book,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.addBook(book);
        return "redirect:/books/";
    }

    @GetMapping("/{id}")
    public String processFetchPersonById(@PathVariable("id") int id, Model model,
                                         @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.getBookById(id));
        Optional<Person> bookOwner = bookDAO.getBookOwner(id);
        if(bookOwner.isPresent()){
            model.addAttribute("owner", bookOwner.get());
        }else{
            model.addAttribute("people", personDAO.getPeople());
        }
        return "books/id";
    }

    @GetMapping("/{id}/edit")
    public String showFormForEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String processEditForm(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.updateBook(id, book);
        return "redirect:/books/";
    }

    @DeleteMapping("/{id}")
    public String processDelete(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books/";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id,
                         @ModelAttribute("person") Person selectedPerson){
        bookDAO.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }
}