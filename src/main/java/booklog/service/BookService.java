package booklog.service;

import booklog.model.Book;
import booklog.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book addBook(Book book) {
        return repository.save(book);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Optional<Book> getBook(Long id) {
        return repository.findById(id);
    }

    public Book updateBook(Long id, Book updatedBook) {
        return repository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setRead(updatedBook.isRead());
                    return repository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public boolean deleteBook(Long id) {
        Optional<Book> optionalBook = repository.findById(id);

        if (optionalBook.isPresent()) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

}
