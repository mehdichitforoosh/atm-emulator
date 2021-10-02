package com.energizeglobal.assignment.card.domain;

import com.energizeglobal.assignment.account.domain.Account;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "card_number", length = 16, nullable = false)
    private String cardNumber;
    @Column(name = "pin", length = 4, nullable = false)
    private String pin;
    @Column(name = "expiration_date", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime expirationDate;
    @Column(name = "active", nullable = false)
    private Boolean active;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Default constructor
    public Card() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public DateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(DateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
