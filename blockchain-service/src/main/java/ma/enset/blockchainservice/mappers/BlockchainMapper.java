package ma.enset.blockchainservice.mappers;

import ma.enset.blockchainservice.dtos.BlockchainDto;
import ma.enset.blockchainservice.entities.Blockchain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BlockchainMapper {

    public Blockchain BlockchainDtoToBlockchain(BlockchainDto blockchainDto);
    public BlockchainDto BlockchainToBlockchainDto(Blockchain blockchain);
}
