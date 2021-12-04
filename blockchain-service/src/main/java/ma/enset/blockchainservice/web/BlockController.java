package ma.enset.blockchainservice.web;

import ma.enset.blockchainservice.dtos.BlockDto;
import ma.enset.blockchainservice.entities.Block;
import ma.enset.blockchainservice.services.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlockController {
    @Autowired
    private BlockService blockService;


    @GetMapping(path = "/block/get/{block_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BlockDto get_block(@PathVariable(name = "block_id") String block_id){
        return blockService.getBlock(block_id);
    }

    @GetMapping(path = "/block/mine/{block_id}")
    public BlockDto mine_block(@PathVariable(name = "block_id") String block_id){
        return blockService.mine_block(block_id);
    }

}
