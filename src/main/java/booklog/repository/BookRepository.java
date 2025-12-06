package booklog.repository;

import booklog.model.Book;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

@Repository
public class BookRepository {

    private final Map<Long, Book> storage = new HashMap<>();
    private Long idCounter = 1L;

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(idCounter++);
        }
        storage.put(book.getId(), book);
        return book;
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }

    public boolean deleteById(Long id) {
        return storage.remove(id) != null;
    }
}
