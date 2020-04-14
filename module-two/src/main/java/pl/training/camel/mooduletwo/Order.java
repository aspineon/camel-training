package pl.training.camel.mooduletwo;

import lombok.Data;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", crlf = "UNIX")
@Data
public class Order {

    @DataField(pos = 1)
    private String name;
    @DataField(pos = 2, precision = 2)
    private String price;
    @DataField(pos = 3)
    private boolean promoProduct;

}
