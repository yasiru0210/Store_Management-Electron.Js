package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data

@NoArgsConstructor
public class Customer {
    private String id;
    private String title;
    private String name;

    public Customer(String id, String title, String name, Date dob, Double salary, String address, String city, String province, String postalcode) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.dob = dob;
        this.salary = salary;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalcode = postalcode;
    }

    private Date dob;
    private Double salary;
    private String address;
    private String city;
    private String province;
    private String postalcode;
}
