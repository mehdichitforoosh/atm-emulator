package com.energizeglobal.assignment.bank.domain;

import javax.persistence.*;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
@Entity
@Table(name = "banks")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    @Column(name = "branch_number", length = 30, nullable = false)
    private String branchNumber;
    @Column(name = "address", length = 200)
    private String address;

    // Default constructor
    public Bank() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
