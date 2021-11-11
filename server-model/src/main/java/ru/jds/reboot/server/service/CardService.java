package ru.jds.reboot.server.service;

import ru.jds.reboot.server.model.Account;
import ru.jds.reboot.server.model.Card;

import java.util.Optional;

public interface CardService {
    void blockCard(Card card);
    boolean isValidCard(Card card);
    Optional<Account> getAccount(Card card);
}
