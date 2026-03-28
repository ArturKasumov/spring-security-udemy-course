package com.arturk.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name="customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;

    private String name;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "create_dt")
    @JsonIgnore
    private Date createDt;

}
