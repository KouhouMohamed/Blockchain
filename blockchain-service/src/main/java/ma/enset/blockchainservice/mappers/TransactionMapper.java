package ma.enset.blockchainservice.mappers;

import ma.enset.blockchainservice.dtos.TransactionDto;
import ma.enset.blockchainservice.entities.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction TransactionDtoToTransaction(TransactionDto transactionDto);
    TransactionDto TransactionToTransactionDto(Transaction transaction);
}
