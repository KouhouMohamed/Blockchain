package ma.enset.blockchainservice.services;

import ma.enset.blockchainservice.dtos.BlockDto;
import ma.enset.blockchainservice.dtos.TransactionDto;
import ma.enset.blockchainservice.entities.Block;
import ma.enset.blockchainservice.entities.Blockchain;
import ma.enset.blockchainservice.entities.Transaction;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

public interface BlockService {
    public BlockDto create_block(Collection<TransactionDto> transactions);
    public void generate_hash(Block block);
    public BlockDto mine_block(String block_id);
    public List<BlockDto> get_block_in_blockchain(String blockchain_id);
    BlockDto getBlock(String block_id);
}
