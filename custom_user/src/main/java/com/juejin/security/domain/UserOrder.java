package com.juejin.security.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String orderNumber;
    private String username;

}
