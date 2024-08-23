package library.borrowing;

import library.book.BookRepository;
import library.book.data.Book;
import library.borrowing.data.BorrowingRecord;
import library.borrowing.data.BorrowingRequest;
import library.user.User;
import library.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import library.borrowing.data.BorrowingRecordDTO;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BorrowingRecordService {

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BorrowingRecordMapper borrowingRecordMapper;


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BorrowingRecordDTO borrowBook(Integer bookId, Integer patronId) {
        Optional<BorrowingRecord> existingBorrowingRecord = borrowingRecordRepository.findByBookIdAndUserIdAndReturnDateIsNull(bookId, patronId);
        if (existingBorrowingRecord.isPresent()) {
            throw new RuntimeException("Book is already borrowed by the patron");
        }
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + bookId));
        Integer copiesNumber = book.getNumber_of_copies();
        if( copiesNumber <= 0)
            throw new RuntimeException("there is no copy to barrow");

        User patron = userRepository.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found with id " + patronId));
        BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                .book(book)
                .user(patron)
                .borrowDate(new Date())
                .returnDate(null)
                .build();

        borrowingRecordRepository.save(borrowingRecord);
        book.setNumber_of_copies(copiesNumber - 1);
        bookRepository.save(book);
        return borrowingRecordMapper.toDTO(borrowingRecord);
    }

    public BorrowingRecordDTO returnBook(Integer bookId, Integer patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository
                .findByBookIdAndUserIdAndReturnDateIsNull(bookId, patronId)
                .orElseThrow(() -> new RuntimeException("Book is not borrowed by the patron"));

        borrowingRecord.setReturnDate(new Date());
        borrowingRecordRepository.save(borrowingRecord);

        Book book = borrowingRecord.getBook();
        book.setNumber_of_copies(book.getNumber_of_copies() + 1);
        bookRepository.save(book);

        return borrowingRecordMapper.toDTO(borrowingRecord);
    }

    public List<BorrowingRecordDTO> getBorrowingHistory(Integer patronId, int page, int size) {
        Page<BorrowingRecord> borrowingRecords = borrowingRecordRepository.findByUserId(patronId, PageRequest.of(page, size));
        return borrowingRecordMapper.toDTOs(borrowingRecords.getContent());
    }


}