package ru.jds.reboot.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "atm")
@Getter
@Setter
@NoArgsConstructor
@Component
public class AtmEntity {
    @Id
    private String name;
    private String code;
    private String role;
}
