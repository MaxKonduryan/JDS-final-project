package ru.jds.reboot.client.model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Balance {
    Currency currency;
    BigDecimal value;
}
