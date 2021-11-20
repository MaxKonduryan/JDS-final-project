package ru.jds.reboot.client.service;

import ru.jds.reboot.client.model.Balance;
import ru.jds.reboot.client.model.Card;

import java.util.Optional;

public interface CardService {
    boolean checkCard(Card card);
    Optional<Balance> checkBalance(Card card);
}
