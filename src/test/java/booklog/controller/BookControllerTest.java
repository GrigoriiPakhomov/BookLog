package booklog.controller;

import booklog.model.Book;
import booklog.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService service;

    @InjectMocks
    private BookController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        Book book = new Book(1L, "Test", "Author", true);

        when(service.addBook(book)).thenReturn(book);

        Book result = controller.addBook(book);

        assertNotNull(result);
        assertEquals("Test", result.getTitle());
        verify(service, times(1)).addBook(book);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(
                new Book(1L, "A", "B", true),
                new Book(2L, "C", "D", false)
        );

        when(service.getAllBooks()).thenReturn(books);

        List<Book> result = controller.getAllBooks();

        assertEquals(2, result.size());
        verify(service, times(1)).getAllBooks();
    }

    @Test
    void testUpdateBook() {
        Book updated = new Book(1L, "Test", "Test", true);

        when(service.updateBook(1L, updated)).thenReturn(updated);

        Book result = controller.updateBook(1L, updated);

        assertEquals("Test", result.getTitle());
        verify(service, times(1)).updateBook(1L, updated);
    }

    @Test
    void testDeleteBook() {
        controller.deleteBook(1L);

        verify(service, times(1)).deleteBook(1L);
    }
}
