package com.sber.reboot.client.model;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * <h2>Класс объекта Банкомат на стороне клиента сервиса</h1>
 * <p>
 * <b> Текущая логика: </b>
 * <p> После создания банкомата возможно вставить карту и запросить баланс.
 * <p>
 * <b> Целевая логика: </b>
 * <p> При создании у банкомата устанавливаются его персональные логин и пароль для подключения к процессингу.
 * <p> Из вне банкомат можно только включить и выключить.
 * <p> В выключенном состоянии банкомат может быть только включен.
 * <p> Во включенном состоянии банкомат:
 * <ul>
 *     <li>пытается подключиться к процессингу и периодически напоминает о себе</li>
 *      <li>ожидает, когда вставят карту</li>
 *      <li>удостоверяет вставленную карту в процессинге</li>
 *      <li>может получить баланс по карте в процессинге, если она действительна</li>
 *      <li>может вернуть карту</li>
 *      <li>может быть выключен</li>
 * </ul>
 */
public abstract class AbstractATM {
    @Getter
    private Card card;

    public AbstractATM() {}

    protected abstract boolean checkCard();
    protected abstract Balance checkBalance();

    public boolean setCard(Card card) {
        if (this.card == null) {
            this.card = card;
            if (checkCard()) {
                return true;
            }
            returnCard();
        }
        return false;
    }

    public boolean returnCard() {
        if (this.card != null) {
            card = null;
            return true;
        }
        return false;
    }

    public Balance getBalance() {
        if (this.card != null) {
            return checkBalance();
        }
        return null;
    }
}
