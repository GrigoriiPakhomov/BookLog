package booklog.controller;

import booklog.model.Book;
import booklog.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // Добавление книги
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    // Получение всех книг
    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return service.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
    }

}

