package ma.enset.blockchainservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blockchain {
    @Id
    private String id;
    private String name;
    private int difficulty;
    private int miningReward;
    @OneToMany(mappedBy = "blockchain")
    private Collection<Block> blocks;

}
