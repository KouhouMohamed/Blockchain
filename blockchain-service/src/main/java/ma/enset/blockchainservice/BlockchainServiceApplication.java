package ma.enset.blockchainservice;

import ma.enset.blockchainservice.dtos.BlockchainDto;
import ma.enset.blockchainservice.entities.Block;
import ma.enset.blockchainservice.entities.Blockchain;
import ma.enset.blockchainservice.entities.Transaction;
import ma.enset.blockchainservice.repositories.BlockRepository;
import ma.enset.blockchainservice.repositories.BlockchainRepository;
import ma.enset.blockchainservice.repositories.TransactionRepository;
import ma.enset.blockchainservice.services.BlockService;
import ma.enset.blockchainservice.services.BlockchainSerice;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BlockchainServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockchainServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BlockService blockService, BlockchainSerice blockchainSerice) {
        return args -> {
            // create a blockchaine with genisis blobk
            BlockchainDto blockchain = blockchainSerice.create_blockchain();

            /*
            // get the hash of the block before mining it
            System.out.println(blockchain.getBlocks().get(0).getHash());
            // cheak if the blockchain is verify before mining the blocks
            System.out.println("Before mine the genisis block");
            System.out.println("Blockchain is verify ? : "+blockchainSerice.verify_blockchain(blockchain));
            // mine the block and re vefify the blockchain
            blockService.mine_block(blockchain.getBlocks().get(0));
            System.out.println("After mine the genisis block");
            System.out.println("Blockchain is verify ? : "+blockchainSerice.verify_blockchain(blockchain));
            System.out.println("Block nonce = "+blockchain.getBlocks().get(0).getNonce());

             */
            /*
            Block b = blockService.create_block(new ArrayList<>());
            b.setBlockchain(blockchain);
            blockService.generate_hash(b);
            System.out.println("************* Hash before mine the block");
            System.out.println(b.getHash());
            blockService.mine_block(b);
            System.out.println("************* Hash after mine the block");
            System.out.println(b.getHash());
            System.out.println("Nonce " + b.getNonce());
             */

        };

    }
}
