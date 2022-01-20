package ma.enset.blockchainservice.web;

import ma.enset.blockchainservice.dtos.BlockDto;
import ma.enset.blockchainservice.dtos.BlockchainDto;
import ma.enset.blockchainservice.dtos.TransactionDto;
import ma.enset.blockchainservice.entities.Block;
import ma.enset.blockchainservice.entities.Blockchain;
import ma.enset.blockchainservice.entities.Transaction;
import ma.enset.blockchainservice.services.BlockchainSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class BlockchainController {
    @Autowired
    BlockchainSerice blockchainSerice;

    @GetMapping(path = "/blockchain/get/{blockchain_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BlockchainDto getBlockchaine(@PathVariable(name = "blockchain_id") String blockchain_id){
        BlockchainDto blockchain = blockchainSerice.get_blockchain(blockchain_id);
        return blockchain;
    }
    @GetMapping(path = "/blockchain/all", produces = { MediaType.APPLICATION_JSON_VALUE } )
    public Collection<BlockchainDto> getAllBlockchains(){

        return blockchainSerice.get_all_blockchains();
    }

    @GetMapping(path = "/blockchain/{blockchain}/blocks")
    public Collection<BlockDto> getBlockchainBlocks(@PathVariable(name = "blockchain") String blockchain){
        return blockchainSerice.get_blockchain_blocks(blockchain);
    }
    @PostMapping(path = "/blockchain/add", produces = { MediaType.APPLICATION_JSON_VALUE },consumes = { MediaType.APPLICATION_JSON_VALUE })
    public BlockchainDto createBlockchain(@RequestBody BlockchainDto blockchain){
        return blockchainSerice.create_blockchain(blockchain);
    }

    @PutMapping(path = "/blockchain/{blockchainId}/pendingTransactions",produces = { MediaType.APPLICATION_JSON_VALUE },consumes = { MediaType.APPLICATION_JSON_VALUE })
    public BlockchainDto pendingTransactions(@PathVariable(name = "blockchainId") String blockchaineId, @RequestBody Collection<TransactionDto> transactions){
        System.out.println("Pending transactions");
        transactions.forEach(transactionDto -> {System.out.println(transactionDto.getDate());});
        return blockchainSerice.pendingTransactions(blockchaineId, transactions);
    }

    @PutMapping(path = "/blockchain/{blockchainName}/mine_block/{blockId}",
            produces = { MediaType.APPLICATION_JSON_VALUE },consumes = { MediaType.APPLICATION_JSON_VALUE })
    public BlockchainDto mine_block(@PathVariable(name = "blockchainName") String blockchainName,@PathVariable(name = "blockId") String blockId){

        return blockchainSerice.mine_block(blockchainName,blockId);
    }
    @GetMapping(path = "/blockchain/{blockchainId}/lastblock",produces = { MediaType.APPLICATION_JSON_VALUE })
    public BlockDto getLastBlock(@PathVariable(name = "blockchainId") String blockchainId){
        return blockchainSerice.get_last_block(blockchainId);
    }
    @PostMapping(path = "/blockchain/{blockchainName}/addblock",produces = { MediaType.APPLICATION_JSON_VALUE })
    public BlockchainDto addBlock(@PathVariable(name = "blockchainName") String blockchainName, @RequestBody BlockDto blockDto){
        return blockchainSerice.add_block(blockchainName, blockDto);
    }
}
