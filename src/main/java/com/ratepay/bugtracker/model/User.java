package com.ratepay.bugtracker.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "login_id", nullable = false)
    private String loginId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

}
