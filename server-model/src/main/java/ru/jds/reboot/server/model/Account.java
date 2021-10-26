package ru.jds.reboot.server.model;

import lombok.Value;

@Value
public class Account {
    Card card;
    Balance balance;
}
