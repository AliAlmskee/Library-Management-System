package library.book.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateBookRequest {

    private Integer id;

    @NotNull
    @NotBlank
    private String author;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private String isbn;
    @NotNull
    private Double price;
    @NotNull
    private Integer number_of_copies;
    private Integer publication_year;

}
