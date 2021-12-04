package ma.enset.blockchainservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class TransactionDto {
    private String id;
    private Date date;
    private String src_address;
    private String dest_address;
    private double amount;
}
