package ma.enset.blockchainservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    @Id
    private String id;
    private Date date;
    private String src_address;
    private String dest_address;
    private double amount;
    @ManyToOne
    private Block block;
}
