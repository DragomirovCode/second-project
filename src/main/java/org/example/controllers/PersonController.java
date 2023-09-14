package org.example.controllers;

import org.example.dao.PersonDAO;
import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {
    private PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String showAllPeople(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "people/show";
    }

    @GetMapping("/new")
    public String showFormForNewPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String processSaveNewPerson(@ModelAttribute("person") Person person) {
        personDAO.addPerson(person);
        return "redirect:/people/";
    }

    @GetMapping("/{id}")
    public String processFetchPersonById(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersonById(id));
        return "people/id";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getPersonById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String processEditForm(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personDAO.updatePerson(id, person);
        return "redirect:/people/";
    }

    @DeleteMapping("/{id}")
    public String processDeletePerson(@PathVariable("id") int id) {
        personDAO.deletePerson(id);
        return "redirect:/people/";
    }
}