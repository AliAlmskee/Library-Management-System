package library.book;

import library.book.data.BookDTO;
import library.book.data.BookRequest;
import library.book.data.Book;

import library.book.data.UpdateBookRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book bookRequestToBook(BookRequest bookRequest);
    Book updateBookRequestToBook(UpdateBookRequest request);
    BookDTO toDTO(Book book);
    List<BookDTO> booksToBookDTOs(List<Book> books);
}