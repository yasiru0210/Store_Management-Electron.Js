package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Item {

    private String itemcode;
    private String description;
    private String packagesize;
    private Double unitprice;
    private Double quantity;

}
