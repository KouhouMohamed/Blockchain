package ma.enset.blockchainservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.blockchainservice.entities.Block;
import ma.enset.blockchainservice.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockchainDto {
    private String id;
    private String name;
    private int difficulty;
    private int miningReward;
    private List<BlockDto> blocks;
}
