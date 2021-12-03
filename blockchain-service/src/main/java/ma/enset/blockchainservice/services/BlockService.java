package ma.enset.blockchainservice.services;

import ma.enset.blockchainservice.entities.Block;
import ma.enset.blockchainservice.entities.Blockchain;
import ma.enset.blockchainservice.entities.Transaction;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface BlockService {
    public Block create_block(Collection<Transaction> transactions);
    public void generate_hash(Block block);
    public Block mine_block(Block block);
}
