package com.example.demo;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private List<String> books = new ArrayList<>();

    @GetMapping
    public List<String> getAllBooks() {
        return books;
    }

    @PostMapping
    public String addBook(@RequestBody String book) {
        books.add(book);
        return "Book added: " + book;
    }

    @DeleteMapping("/{index}")
    public String deleteBook(@PathVariable int index) {
        if (index < 0 || index >= books.size()) {
            return "Invalid index.";
        }
        String removedBook = books.remove(index);
        return "Book removed: " + removedBook;
    }
}