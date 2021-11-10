package ru.jds.reboot.client.model;

import lombok.RequiredArgsConstructor;
import ru.jds.reboot.client.service.CardService;

import java.util.Optional;

/**
 * Банкомат с состоянием атрибута Карта.
 *
 * Пользователь предъявляет карту в виде номера и пин-кода.
 * Если данные карты проходят проверку в Процессинге (ПЦ),
 * то карты принимается банкоматом и в принципе возможна
 * дальнейшая работа с картой.
 *
 * Доступные операции: проверка карты и баланса карты.
 *
 * Необходима внешняя реализация методов отвечающих
 * за сетевое взаимодействие с Процессингом.
 */
@RequiredArgsConstructor
public class Atm {
    private Card card;

    private final CardService cardService;

    public Optional<Card> getCard() {
        return Optional.ofNullable(card);
    }

    public boolean acceptCard(Card card) {
        if (this.card == null && cardService.checkCard(card)) {
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
        return Optional.ofNullable(card).flatMap(this.cardService::checkBalance);
    }
}
