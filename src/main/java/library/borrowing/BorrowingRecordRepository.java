package library.borrowing;

import library.borrowing.data.BorrowingRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    Optional<BorrowingRecord> findByBookIdAndUserIdAndReturnDateIsNull(Integer bookId, Integer patronId);

    Optional<BorrowingRecord> findByBookIdAndUserId(Integer bookId, Integer patronId);

    Page<BorrowingRecord> findByUserId(Integer patronId, PageRequest of);
}