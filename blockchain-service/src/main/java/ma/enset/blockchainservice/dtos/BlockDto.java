package ma.enset.blockchainservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.blockchainservice.entities.Blockchain;
import ma.enset.blockchainservice.entities.Transaction;
import org.mapstruct.control.MappingControl;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockDto {
    private String id;
    private Date create_date;
    private String hash;
    private String previous_hash;
    private int nonce;
    private Collection<TransactionDto> transactions;

}
