package ru.jds.reboot.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Card {
    String number;
    String pinCode;
}
