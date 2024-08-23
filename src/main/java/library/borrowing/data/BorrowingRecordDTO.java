package library.borrowing.data;

import lombok.Data;

import java.util.Date;

@Data
public class BorrowingRecordDTO {

    private Integer id;
    private Integer bookId;
    private Integer userId;
    private Date borrowDate;
    private Date returnDate;


}