package library.book.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Integer id;
    private String author;
    private String title;
    private String description;
    private Double price;
    private Integer number_of_copies;
    private String isbn;
    private Integer publication_year;

}
