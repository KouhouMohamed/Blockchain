package ma.enset.blockchainservice.mappers;

import ma.enset.blockchainservice.dtos.BlockDto;
import ma.enset.blockchainservice.entities.Block;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BlockMapper {

    @Mapping(target = "blockchain", ignore = true)
    Block BlockDtoToBlock(BlockDto blockDto);
    BlockDto BlockToBlockDto(Block block);
}
