package ru.jds.reboot.server.model;

import lombok.AllArgsConstructor;
import ru.jds.reboot.server.service.CardService;

import java.util.Optional;

/**
 * Процессинг
 *
 * Отрабатывает запросы с банкоматов
 *
 * Доступные операции: проверка карты и баланса карты.
 *
 * Необходима внешняя реализация методов отвечающих
 * за взаимодействие с хранилищем данных.
 */
@AllArgsConstructor
public class Processing {

    private CardService cardService;

    public void blockCard(Card card) {
        cardService.blockCard(card);
    }
    public boolean checkCard(Card card) {
        return cardService.isValidCard(card);
    }
    public Optional<Balance> checkBalance(Card card) {
        return cardService.getAccount(card).map(Account::getBalance);
    }
}
