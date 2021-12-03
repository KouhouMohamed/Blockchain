package ma.enset.blockchainservice;

import ma.enset.blockchainservice.entities.Block;
import ma.enset.blockchainservice.entities.Blockchain;
import ma.enset.blockchainservice.entities.Transaction;
import ma.enset.blockchainservice.repositories.BlockRepository;
import ma.enset.blockchainservice.repositories.BlockchainRepository;
import ma.enset.blockchainservice.repositories.TransactionRepository;
import ma.enset.blockchainservice.services.BlockService;
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
    CommandLineRunner start(BlockService blockService) {
        return args -> {
            Blockchain blockchain = new Blockchain(UUID.randomUUID().toString(),"blockchain",2,
                    2, null);
            Block b = blockService.create_block(new ArrayList<>());
            b.setBlockchain(blockchain);
            blockService.generate_hash(b);
            System.out.println("************* Hash before mine the block");
            System.out.println(b.getHash());
            blockService.mine_block(b);
            System.out.println("************* Hash after mine the block");
            System.out.println(b.getHash());
            System.out.println("Nonce " + b.getNonce());

        };

    }
}
