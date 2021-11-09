package ru.jds.reboot.server.model;

import lombok.Value;

import java.util.Set;

@Value
public class Account {
    Set<Card> cards;
    Balance balance;
}
