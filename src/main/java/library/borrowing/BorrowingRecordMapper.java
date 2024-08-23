package library.borrowing;

import library.borrowing.data.BorrowingRecord;
import library.borrowing.data.BorrowingRecordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BorrowingRecordMapper {

      @Mapping(source = "book.id", target = "bookId")
      @Mapping(source = "user.id", target = "userId")
    BorrowingRecordDTO toDTO(BorrowingRecord borrowingRecord);

    List<BorrowingRecordDTO> toDTOs(List<BorrowingRecord> borrowingRecords);
}