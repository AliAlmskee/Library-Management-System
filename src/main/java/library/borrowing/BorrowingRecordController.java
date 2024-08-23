package library.borrowing;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import library.borrowing.data.BorrowingRecord;
import library.borrowing.data.BorrowingRecordDTO;
import library.borrowing.data.BorrowingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borrow")
@RequiredArgsConstructor
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingRecordService;


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDTO> borrowBook(
            @PathVariable Integer bookId,
            @PathVariable Integer patronId
         ) {
        BorrowingRecordDTO borrowingRecord = borrowingRecordService.borrowBook(bookId, patronId);
        return new ResponseEntity<>(borrowingRecord, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDTO> returnBook(@PathVariable Integer bookId, @PathVariable Integer patronId) {
        BorrowingRecordDTO borrowingRecord = borrowingRecordService.returnBook(bookId, patronId);
        return new ResponseEntity<>(borrowingRecord, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/history/{patronId}")
    public ResponseEntity<List<BorrowingRecordDTO>> getBorrowingHistory( @NotNull  @PathVariable Integer patronId,
                                                                        @RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int size) {
        List<BorrowingRecordDTO> borrowingRecords = borrowingRecordService.getBorrowingHistory(patronId, page, size);
        return ResponseEntity.ok(borrowingRecords);
    }

}