package org.kodluyoruz.mybank.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.kodluyoruz.mybank.customer.Customer;

import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String city;

    private String country;

    private String line1;

    private String line2;

    private int postalCode;

    private String state;

    @OneToOne(mappedBy = "address",fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

}
