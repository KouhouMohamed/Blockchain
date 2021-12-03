package ma.enset.blockchainservice.servicesImpl;

import ma.enset.blockchainservice.entities.Block;
import ma.enset.blockchainservice.entities.Transaction;
import ma.enset.blockchainservice.services.BlockService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.UUID;

@Service
@Transactional
public class BlockServiceImpl implements BlockService {

    // operation to generate a block's hash
    @Override
    public void generate_hash(Block block){
        try {
            String data =block.getHashCode();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            block.setHash(DatatypeConverter.printHexBinary(hash));
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public Block create_block(Collection<Transaction> transactions) {
        Block block = new Block();
        block.setId(UUID.randomUUID().toString());
        return block;
    }

    @Override
    public Block mine_block(Block block) {
        // mine the block (sign the block)
            String sub;
            String hash_start = "";
            for(int i=0; i<block.getBlockchain().getDifficulty();i++){hash_start=hash_start+"0";}
            do {
                generate_hash(block);
                sub = block.getHash().substring(0, block.getBlockchain().getDifficulty());
                block.setNonce(block.getNonce()+1);
            }while (!hash_start.equals(sub));
            return block;
    }
}
