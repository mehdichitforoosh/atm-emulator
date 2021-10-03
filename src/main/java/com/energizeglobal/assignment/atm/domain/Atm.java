package com.energizeglobal.assignment.atm.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 * ATM JPA entity
 */
@Entity
@Table(name = "atms")
public class Atm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "atm_number", length = 10, nullable = false)
    private String atmNumber;
    @Column(name = "available_cash", nullable = false)
    private BigDecimal availableCash;

    // Default constructor
    public Atm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAtmNumber() {
        return atmNumber;
    }

    public void setAtmNumber(String atmNumber) {
        this.atmNumber = atmNumber;
    }

    public BigDecimal getAvailableCash() {
        return availableCash;
    }

    public void setAvailableCash(BigDecimal availableCash) {
        this.availableCash = availableCash;
    }
}
