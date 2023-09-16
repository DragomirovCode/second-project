package org.example.dao;

import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks(){
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public Book getBookById(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",
                new Object[]{id}, new BookMapper()).stream().findAny().orElse(null);
    }

    public void addBook(Book book){
        jdbcTemplate.update("INSERT INTO Book (title, author, year) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void updateBook(int id, Book updatedBookData){
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?",
                updatedBookData.getTitle(), updatedBookData.getAuthor(), updatedBookData.getYear(), id);
    }

    public void deleteBook(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }
}