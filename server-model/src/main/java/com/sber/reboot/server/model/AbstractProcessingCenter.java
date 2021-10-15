package com.sber.reboot.server.model;

public abstract class AbstractProcessingCenter {

    protected abstract boolean existAccount(Card card);
    protected abstract Account getAccount(Card card);

    protected boolean checkCard(Card card) {
        return existAccount(card);
    }
    protected Balance checkBalance(Card card) {
        Account account = getAccount(card);
        return (account != null) ? account.getBalance() : null;
    }
}
