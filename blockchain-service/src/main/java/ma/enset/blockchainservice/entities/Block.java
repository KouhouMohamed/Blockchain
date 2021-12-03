package ma.enset.blockchainservice.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    private Blockchain blockchain;

    public Block(Collection<Transaction> transactions, Date date) {
        this.nonce=0;
        this.transactions = transactions;
        this.create_date = date;
        this.previous_hash=null;
        //this.blockchain=new Blockchain();

    }
    // re implements function that return hash code
    public String getHashCode(){
        return String.valueOf(this.previous_hash)+nonce+transactions.hashCode()+String.valueOf(this.blockchain.hashCode());
    }
}
