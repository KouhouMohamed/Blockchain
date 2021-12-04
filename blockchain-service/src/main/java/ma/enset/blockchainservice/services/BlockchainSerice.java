package ma.enset.blockchainservice.services;

import ma.enset.blockchainservice.dtos.BlockDto;
import ma.enset.blockchainservice.dtos.BlockchainDto;
import ma.enset.blockchainservice.dtos.TransactionDto;
import ma.enset.blockchainservice.entities.Block;
import ma.enset.blockchainservice.entities.Blockchain;
import ma.enset.blockchainservice.entities.Transaction;

import java.util.Collection;

public interface BlockchainSerice {
    public BlockchainDto get_blockchain(String id);
    public BlockchainDto pendingTransactions(String blockchain_id, Collection<TransactionDto> transactionDtos);
    public BlockchainDto create_blockchain();
    public BlockchainDto mine_block(String blockchain_id, String miner_block_id);
    public BlockDto get_last_block(String blockchain_id);
    public boolean verify_blockchain(String blockchain_id);
    Collection<BlockchainDto> get_all_blockchains();
    Collection<BlockDto> get_blockchain_blocks(String blockchain);
}
