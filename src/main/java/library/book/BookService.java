package library.book;

import library.aspect.Loggable;
import library.book.data.Book;
import library.book.data.BookDTO;
import library.book.data.BookRequest;
import library.book.data.UpdateBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookMapper bookMapper;


    @Loggable
    public ResponseEntity<BookDTO> save(BookRequest request) {
        Book book = repository.save(bookMapper.bookRequestToBook(request));
        return ResponseEntity.ok(bookMapper.toDTO(book));
    }

    @Loggable
    @Cacheable(value = "books", key = "#root.targetClass + #root.methodName")
    public List<BookDTO> findAll() {
        return bookMapper.booksToBookDTOs(repository.findAll());
    }

    @Loggable
    @Cacheable(value = "books", key = "#id")
    public BookDTO findById(Integer id) {
        Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id " + id));
        return bookMapper.toDTO(book);
    }

    @CachePut(value = "books", key = "#id")
    public BookDTO update(Integer id,UpdateBookRequest request) {
        Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id " + id));
        request.setId(id);
        Book updatedBook = repository.save(bookMapper.updateBookRequestToBook(request));
        return bookMapper.toDTO(updatedBook);
    }
    @CacheEvict(value = "books", key = "#id")
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}