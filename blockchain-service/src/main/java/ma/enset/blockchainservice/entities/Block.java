package ma.enset.blockchainservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Block {
    @Id
    private String id;
    private Date create_date;
    private String hash;
    private String previous_hash;
    private int nonce;

    @OneToMany(mappedBy = "block")
    private Collection<Transaction> transactions;

    @JsonIgnore
    @ManyToOne
    private Blockchain blockchain;

    public Block(String id, Collection<Transaction> transactions, Date date) {
        this.id = id;
        this.nonce=0;
        this.transactions = transactions;
        this.create_date = date;


    }
    @Override
    // re implements function that return hash code
    public int hashCode(){
        int hashcode = nonce+this.create_date.hashCode();
        hashcode = (this.previous_hash!=null)?hashcode+this.previous_hash.hashCode():hashcode;
        hashcode = (this.transactions!=null)?hashcode+this.transactions.hashCode():hashcode;
        hashcode = (this.blockchain!=null)?hashcode+this.blockchain.hashCode():hashcode;
        return hashcode;
    }
    public boolean isMine(){
        String hash_start = "";
        if(this.getBlockchain()!=null) {
            int difficulty = this.getBlockchain().getDifficulty();
            for (int i = 0; i < difficulty; i++) {
                hash_start = hash_start + "0";
            }
            String sub = this.getHash().substring(0, difficulty);
            return hash_start.equals(sub);
        }
        return false;
    }

    public void setHash(String hash) {
        this.hash = hash;
        isMine();
    }
}
