package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String id;
    private String title;
    private String name;
    private Date dob;
    private Double salary;
    private String address;
    private String city;
    private String province;
    private String postalcode;
}
