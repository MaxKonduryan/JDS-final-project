package ru.jds.reboot.server.model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Balance {
    Currency currency;
    BigDecimal value;
}
