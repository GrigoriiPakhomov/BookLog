package booklog.service;

import booklog.model.Book;
import booklog.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    private BookRepository repository;
    private BookService service;

    @BeforeEach
    void setup() {
        repository = mock(BookRepository.class);
        service = new BookService(repository);
    }

    @Test
    void testAddBook() {
        Book book = new Book();
        book.setTitle("Test");
        book.setAuthor("Author");

        when(repository.save(any(Book.class))).thenReturn(book);

        Book result = service.addBook(book);
        assertNotNull(result);
        assertEquals("Test", result.getTitle());
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = List.of(new Book(), new Book());
        when(repository.findAll()).thenReturn(books);

        List<Book> result = service.getAllBooks();
        assertEquals(2, result.size());
    }

    @Test
    void testGetBook() {
        Book book = new Book();
        book.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = service.getBook(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testUpdateBook() {
        Book existing = new Book();
        existing.setId(1L);

        Book updated = new Book();
        updated.setTitle("New Title");
        updated.setAuthor("New Author");
        updated.setRead(true);

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Book.class))).thenReturn(existing);

        Book result = service.updateBook(1L, updated);

        assertEquals("New Title", result.getTitle());
        assertEquals("New Author", result.getAuthor());
        assertTrue(result.isRead());
    }

    @Test
    void testDeleteBookSuccess() {
        Book book = new Book();
        book.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        boolean result = service.deleteBook(1L);

        assertTrue(result);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBookNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        boolean result = service.deleteBook(1L);

        assertFalse(result);
        verify(repository, never()).deleteById(anyLong());
    }
}
