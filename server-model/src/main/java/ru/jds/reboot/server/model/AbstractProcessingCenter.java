package ru.jds.reboot.server.model;

import java.util.Optional;

public abstract class AbstractProcessingCenter {

    protected abstract boolean isValidCard(Card card);
    protected abstract Optional<Account> getAccount(Card card);

    public boolean checkCard(Card card) {
        return isValidCard(card);
    }
    public Optional<Balance> checkBalance(Card card) {
        return getAccount(card).map(Account::getBalance);
    }
}
