package library.borrowing.data;


import lombok.Data;

import java.util.Date;

@Data
public class BorrowingRequest {
    private Date borrowDate;
    private Date returnDate;


}
