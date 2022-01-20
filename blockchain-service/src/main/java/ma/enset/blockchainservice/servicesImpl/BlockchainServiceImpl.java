package ma.enset.blockchainservice.servicesImpl;

import ma.enset.blockchainservice.dtos.BlockDto;
import ma.enset.blockchainservice.dtos.BlockchainDto;
import ma.enset.blockchainservice.dtos.TransactionDto;
import ma.enset.blockchainservice.entities.Block;
import ma.enset.blockchainservice.entities.Blockchain;
import ma.enset.blockchainservice.entities.Transaction;
import ma.enset.blockchainservice.mappers.BlockMapper;
import ma.enset.blockchainservice.mappers.BlockchainMapper;
import ma.enset.blockchainservice.mappers.TransactionMapper;
import ma.enset.blockchainservice.repositories.BlockRepository;
import ma.enset.blockchainservice.repositories.BlockchainRepository;
import ma.enset.blockchainservice.services.BlockService;
import ma.enset.blockchainservice.services.BlockchainSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.*;

@Service
@Transactional
public class BlockchainServiceImpl implements BlockchainSerice {
    @Autowired
    TransactionMapper transactionMapper;
    @Autowired
    BlockService blockService;
    @Autowired
    BlockchainRepository blockchainRepository;
    @Autowired
    BlockRepository blockRepository;
    @Autowired
    BlockchainMapper blockchainMapper;
    @Autowired
    BlockMapper blockMapper;


    @Override
    public BlockchainDto get_blockchain(String blockchainName) {
        return blockchainMapper.BlockchainToBlockchainDto(blockchainRepository.findByName(blockchainName));
    }

    @Override
    public BlockchainDto add_block(String blockchainName, BlockDto blockDto) {
        Blockchain blockchain = blockchainRepository.findByName(blockchainName);
        if (blockchain != null){
            Block block = blockMapper.BlockDtoToBlock(blockDto);
            block.setBlockchain(blockchain);
            block.setPrevious_hash(get_last_block(blockchain.getId()).getHash());
            blockRepository.save(block);

        }
        return blockchainMapper.BlockchainToBlockchainDto(blockchainRepository.findByName(blockchainName));
    }

    @Override
    public BlockchainDto pendingTransactions(String blockchainName, Collection<TransactionDto> transactionDtos) {
        //System.out.println("Pending transactions method");
        BlockDto block = blockService.create_block(transactionDtos);
        mine_block(blockchainName,block.getId());
        return get_blockchain(blockchainName);
    }

    @Override
    public BlockchainDto create_blockchain(BlockchainDto blockchainDTO) {

        Blockchain blockchain = new Blockchain();
        blockchain.setId(UUID.randomUUID().toString());
        blockchain.setName(blockchainDTO.getName());
        blockchain.setDifficulty(blockchainDTO.getDifficulty());
        blockchain.setMiningReward(blockchainDTO.getMiningReward());
        blockchainDTO.setBlocks(null);

        BlockDto genisisBlock = blockService.create_block(new ArrayList<>());

        Blockchain blockchain1 = blockchainRepository.save(blockchain);
        //BlockchainDto blockchainDto = mine_block(blockchain.getId(),genisisBlock.getId());
        Block block = blockMapper.BlockDtoToBlock(genisisBlock);
        block.setBlockchain(blockchain);

        blockRepository.save(block);
        blockService.mine_block(genisisBlock.getId());
        return get_blockchain(blockchain.getId());
    }

    @Override
    public BlockchainDto mine_block(String blockchainName, String miner_block_id) {
        BlockchainDto blockchainDto = get_blockchain(blockchainName);
        Blockchain blockchain = blockchainMapper.BlockchainDtoToBlockchain(blockchainDto);
        Block miner_block = blockRepository.findById(miner_block_id).get();
        if(blockchain!=null && miner_block!=null){
            String previous_hash = (get_last_block(blockchain.getId())!=null)?get_last_block(blockchainName).getHash():null;
            miner_block.setPrevious_hash(previous_hash);
            miner_block.setBlockchain(blockchain);
            blockRepository.save(miner_block);
            blockService.mine_block(miner_block.getId());
        }
        if (miner_block==null) return null;
        return get_blockchain(blockchainName);
    }

    @Override
    public BlockDto get_last_block(String blockchain_id) {
        Blockchain blockchain = blockchainRepository.findById(blockchain_id).get();
        if(blockchain!=null){
            List<BlockDto> blocks= blockService.get_block_in_blockchain(blockchain_id);
            System.out.println("******* : "+blocks.size());
            if (blocks!=null ) return blocks.get(blocks.size()-1);
        }
        return null;
    }

    @Override
    public boolean verify_blockchain(String blockchainName) {
        BlockchainDto blockchain = get_blockchain(blockchainName);
        if (blockchain==null) return false;
        Collection<Block> blocks = new ArrayList<>();
        blockchain.getBlocks().forEach(blockDto -> {
            blocks.add(blockMapper.BlockDtoToBlock(blockDto));
        });
        for (Block b :blocks ){if(!b.isMine()) return false;}
        return true;
    }

    @Override
    public Collection<BlockchainDto> get_all_blockchains() {
        Collection<BlockchainDto>  blockchainDtos =new ArrayList<>();
        Collection<BlockDto> blockDtos = new ArrayList<>();
        Collection<TransactionDto> transactionDtos = new ArrayList<>();

        blockchainRepository.findAll().forEach(blockchain -> {
            BlockchainDto blockchainDto = blockchainMapper.BlockchainToBlockchainDto(blockchain);
            //blockchainDto.setBlocks(blockService.get_block_in_blockchain(blockchainDto.getId()));
            blockchainDtos.add(blockchainDto);
        });
        return blockchainDtos;
    }

    @Override
    public Collection<BlockDto> get_blockchain_blocks(String blockchainName) {
        BlockchainDto blockchain = get_blockchain(blockchainName);
        if(blockchain!=null){return blockchain.getBlocks();}
        return null;
    }
}
