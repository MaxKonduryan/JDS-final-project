package ru.jds.reboot.client.model;

import java.util.Optional;

/**
 * Абстрактный банкомат с состоянием атрибута Карта.
 *
 * Пользователь предъявляет карту в виде номера и пин-кода.
 * Если данные карты проходят проверку в Процессинге (ПЦ),
 * то карты принимается банкоматом и в принципе возможна
 * дальнейшая работа с картой.
 *
 * Доступные операции: проверка баланса.
 *
 * Необходима внешняя реализация методов отвечающих
 * за сетевое взаимодействие с Процессингом.
 */
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
