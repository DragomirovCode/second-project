package org.example.dao;

import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople(){
        return jdbcTemplate.query("SELECT * FROM Person",new PersonMapper());
    }

    public Person getPersonById(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);
    }

    public void updatePerson(int id, Person updatedPersonData){
        jdbcTemplate.update("UPDATE Person SET name=?, age=? WHERE id=?",
                updatedPersonData.getName(), updatedPersonData.getAge(), id);
    }

    public void addPerson(Person person){
        jdbcTemplate.update("INSERT INTO Person (name, age) VALUES (?, ?)", person.getName(),
                person.getAge());
    }
    public void deletePerson(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}