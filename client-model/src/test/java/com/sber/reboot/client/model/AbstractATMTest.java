package com.sber.reboot.client.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AbstractATMTest {

    AbstractATM atm;

    @BeforeEach
    void beforeEach() {
        atm = mock(
                AbstractATM.class,
                withSettings()
                        .useConstructor()
                        .defaultAnswer(CALLS_REAL_METHODS)
        );
    }

    @Test
    @DisplayName("Constructor - no card")
    void emptyCardWhenConstruct() {
        assertNull(atm.getCard());
    }

    @Test
    @DisplayName("Card - correct pin code")
    void correctCardPinCode() {
        when(atm.checkCard()).thenReturn(true);
        assertTrue(atm.setCard(new Card("1000200030004000", "0000")));
    }

    @Test
    @DisplayName("Card - invalid pin code")
    void invalidCardPinCode() {
        when(atm.checkCard()).thenReturn(false);
        assertFalse(atm.setCard(new Card("1000200030004000", "0000")));
    }

    @Test
    @DisplayName("Card - there already")
    void alreadyCardThere() {
        when(atm.checkCard()).thenReturn(true);
        atm.setCard(new Card("1000200030004001", "0000"));
        assertFalse(atm.setCard(new Card("1000200030004002", "1111")));
    }

    @Test
    @DisplayName("Card - balance without card")
    void getCardBalanceWithoutCard() {
        Balance balance = new Balance(Currency.RUR, BigDecimal.TEN);

        when(atm.checkBalance()).thenReturn(balance);

        assertNull(atm.getBalance());
    }

    @Test
    @DisplayName("Card - balance with card")
    void getCardBalanceWithCard() {
        Balance balance = new Balance(Currency.RUR, BigDecimal.TEN);

        when(atm.checkCard()).thenReturn(true);
        when(atm.checkBalance()).thenReturn(balance);

        atm.setCard(new Card("1000200030004001", "0000"));
        assertEquals(balance.getCurrency(), atm.getBalance().getCurrency());
        assertEquals(balance.getValue(), atm.getBalance().getValue());
    }
}
