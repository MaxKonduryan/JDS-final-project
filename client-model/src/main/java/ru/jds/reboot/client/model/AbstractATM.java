package ru.jds.reboot.client.model;

import java.util.Optional;

public abstract class AbstractATM {
    private Card card;

    public AbstractATM() {
        card = null;
    }

    protected abstract boolean checkCard(Card card);
    protected abstract Optional<Balance> checkBalance(Card card);

    public Optional<Card> getCard() {
        return Optional.ofNullable(card);
    }

    public boolean acceptCard(Card card) {
        if (this.card == null && checkCard(card)) {
            this.card = card;
            return true;
        }
        return false;
    }

    public void clearCard() {
        if (card != null) {
            card = null;
        }
    }

    public boolean isPresentCard() {
        return card != null;
    }

    public Optional<Balance> getBalance() {
        return Optional.ofNullable(card).flatMap(this::checkBalance);
    }
}
