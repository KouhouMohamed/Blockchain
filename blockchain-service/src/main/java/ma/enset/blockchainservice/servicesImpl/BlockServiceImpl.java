package ma.enset.blockchainservice.servicesImpl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ma.enset.blockchainservice.dtos.BlockDto;
import ma.enset.blockchainservice.dtos.TransactionDto;
import ma.enset.blockchainservice.entities.Block;
import ma.enset.blockchainservice.entities.Blockchain;
import ma.enset.blockchainservice.entities.Transaction;
import ma.enset.blockchainservice.mappers.BlockMapper;
import ma.enset.blockchainservice.mappers.TransactionMapper;
import ma.enset.blockchainservice.repositories.BlockRepository;
import ma.enset.blockchainservice.repositories.TransactionRepository;
import ma.enset.blockchainservice.services.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

@Service
@Transactional
public class BlockServiceImpl implements BlockService {
    @Autowired
    BlockRepository blockRepository;

    @Autowired
    BlockMapper blockMapper;

    @Autowired
    TransactionMapper transactionMapper;

    @Autowired
    TransactionRepository transactionRepository;

    // operation to generate a block's hash
    @Override
    public void generate_hash(Block block){
        try {
                int data =block.hashCode();
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(String.valueOf(data).getBytes(StandardCharsets.UTF_8));
                block.setHash(DatatypeConverter.printHexBinary(hash));
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public BlockDto create_block(Collection<TransactionDto> transactionsDto) {
        Collection<Transaction> transactions = new ArrayList<>();
        Block block = new Block(UUID.randomUUID().toString(),null, new Date());
        //blockRepository.save(block);

        transactionsDto.forEach(transactionDto -> {
            Transaction transaction = transactionMapper.TransactionDtoToTransaction(transactionDto);
            transaction.setBlock(block);
            transactions.add(transaction);
            transactionRepository.save(transaction);
        });
        generate_hash(block);
        blockRepository.save(block);
        return blockMapper.BlockToBlockDto(block);
    }

    @Override
    public BlockDto mine_block(String block_id) {
        // mine the block (sign the block)
            String sub;
            String hash_start = "";
            Block block = blockRepository.getById(block_id);
            if (block!=null){
                for(int i=0; i<block.getBlockchain().getDifficulty();i++){hash_start=hash_start+"0";}
                do {
                    generate_hash(block);
                    sub = block.getHash().substring(0, block.getBlockchain().getDifficulty());
                    block.setNonce(block.getNonce()+1);
                    //System.out.println(block.getHash());
                }while (!hash_start.equals(sub));
                blockRepository.save(block);
            }
            return blockMapper.BlockToBlockDto(block);
    }

    @Override
    public List<BlockDto> get_block_in_blockchain(String blockchain_id) {
        List<Block> blocks = blockRepository.findAllByBlockchainId(blockchain_id);
        List<BlockDto> blocksDto = new ArrayList<>();
        blocks.forEach(block -> {

            BlockDto blockDto =blockMapper.BlockToBlockDto(block);
            blocksDto.add(blockDto);
        });

        return blocksDto;
    }

    @Override
    public BlockDto getBlock(String block_id) {
        Block block = blockRepository.getById(block_id);
        Collection<Transaction> transactions = transactionRepository.findAllByBlock(block_id);
        //block.setTransactions(transactions);
        return blockMapper.BlockToBlockDto(block);
    }
}
