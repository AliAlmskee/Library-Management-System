package library.book;

import library.book.data.BookDTO;
import library.book.data.BookRequest;
import library.book.data.Book;

import library.book.data.UpdateBookRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book bookRequestToBook(BookRequest bookRequest);
    Book updateBookRequestToBook(UpdateBookRequest request);
    @Mapping(target = "author", source = "author")
    @Mapping(target = "title", source = "title")
    BookDTO toDTO(Book book);
    List<BookDTO> booksToBookDTOs(List<Book> books);
}