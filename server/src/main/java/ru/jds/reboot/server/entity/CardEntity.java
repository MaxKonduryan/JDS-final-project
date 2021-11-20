package ru.jds.reboot.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
public class CardEntity {
    @Id
    @Column(name = "number")
    private String number;

    @Column(name = "pincode")
    private String pinCode;

    @Column(name = "blocked")
    private boolean blocked;

    @ManyToOne
    @JoinColumn(name = "account", nullable = false)
    private AccountEntity account;
}
