package library.book;

import library.book.data.Book;
import library.book.data.BookDTO;
import library.book.data.BookRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookServiceTest {

    @InjectMocks
    private  BookService bookService;

    @Mock
    private BookRepository repository ;

    @Mock
    private BookMapper bookMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_book()
    {
        BookRequest bookRequest = BookRequest.builder()
                .author("J.K. Rowling")
                .title("Harry Potter and the Philosopher's Stone")
                .description("A young wizard's journey begins...")
                .price(19.99)
                .number_of_copies(3)
                .isbn("978-0-7475-3269-9")
                .publication_year(2000)
                .build();

        Book book = Book.builder()
                .author("J.K. Rowling")
                .title("Harry Potter and the Philosopher's Stone")
                .description("A young wizard's journey begins...")
                .price(19.99)
                .number_of_copies(3)
                .isbn("978-0-7475-3269-9")
                .publication_year(2000)
                .build();

        BookDTO bookDTO = BookDTO.builder()
                .author("J.K. Rowling")
                .title("Harry Potter and the Philosopher's Stone")
                .description("A young wizard's journey begins...")
                .price(19.99)
                .number_of_copies(3)
                .isbn("978-0-7475-3269-9")
                .publication_year(2000)
                .build();

        when(bookMapper.bookRequestToBook(bookRequest)).thenReturn(book);
        when(bookMapper.toDTO(book)).thenReturn(bookDTO);
        when(repository.save(any(Book.class))).thenReturn(book);

        ResponseEntity<BookDTO> responseDTO = bookService.save(bookRequest);
        BookDTO savedBookDTO = responseDTO.getBody();

        assertEquals(bookRequest.getAuthor(), savedBookDTO.getAuthor());
        assertEquals(bookRequest.getTitle(), savedBookDTO.getTitle());
        assertEquals(bookRequest.getDescription(), savedBookDTO.getDescription());
        assertEquals(bookRequest.getPrice(), savedBookDTO.getPrice());
        assertEquals(bookRequest.getNumber_of_copies(), savedBookDTO.getNumber_of_copies());
        assertEquals(bookRequest.getIsbn(), savedBookDTO.getIsbn());
        assertEquals(bookRequest.getPublication_year(), savedBookDTO.getPublication_year());
    }
}