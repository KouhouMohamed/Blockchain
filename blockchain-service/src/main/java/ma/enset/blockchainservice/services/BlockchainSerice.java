package ma.enset.blockchainservice.services;

import ma.enset.blockchainservice.dtos.BlockDto;
import ma.enset.blockchainservice.dtos.BlockchainDto;
import ma.enset.blockchainservice.dtos.TransactionDto;
import ma.enset.blockchainservice.entities.Block;
import ma.enset.blockchainservice.entities.Blockchain;
import ma.enset.blockchainservice.entities.Transaction;

import java.util.Collection;

public interface BlockchainSerice {
    public BlockchainDto get_blockchain(String blockchainName);
    public BlockchainDto add_block(String blockchainName, BlockDto blockDto);
    public BlockchainDto pendingTransactions(String blockchainName, Collection<TransactionDto> transactionDtos);
    public BlockchainDto create_blockchain(BlockchainDto blockchainDto);
    public BlockchainDto mine_block(String blockchainName, String miner_block_id);
    public BlockDto get_last_block(String blockchainName);
    public boolean verify_blockchain(String blockchainName);
    Collection<BlockchainDto> get_all_blockchains();
    Collection<BlockDto> get_blockchain_blocks(String blockchain);
}
