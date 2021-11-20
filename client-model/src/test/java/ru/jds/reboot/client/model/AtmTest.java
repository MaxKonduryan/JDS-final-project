package ru.jds.reboot.client.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.jds.reboot.client.service.CardService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AtmTest {

    CardService cardService;
    Atm atm;

    @BeforeEach
    void beforeEach() {
        cardService = mock(CardService.class);
        atm = new Atm(cardService);
    }

    @Test
    @DisplayName("Constructor - empty card")
    void emptyCardWhenConstruct() {
        assertNull(atm.getCard().orElse(null));
        assertEquals(Optional.empty(), atm.getCard());
    }

    @Test
    @DisplayName("Card - correct pin code")
    void correctCardPinCode() {
        Card card = new Card("1000200030004000", "0000");

        when(cardService.checkCard(card)).thenReturn(true);

        assertTrue(atm.acceptCard(card));
    }

    @Test
    @DisplayName("Card - invalid pin code")
    void invalidCardPinCode() {
        Card card = new Card("1000200030004000", "0000");

        when(cardService.checkCard(card)).thenReturn(false);

        assertFalse(atm.acceptCard(card));
    }

    @Test
    @DisplayName("Card - there already")
    void alreadyCardThere() {
        Card card = new Card("1000200030004001", "0000");

        when(cardService.checkCard(card)).thenReturn(true);

        atm.acceptCard(card);
        assertFalse(atm.acceptCard(new Card("1000200030004002", "1111")));
    }


    @Test
    @DisplayName("Card - balance with card")
    void getCardBalanceWithCard() {
        Balance balance = new Balance(Currency.RUR, BigDecimal.TEN);
        Card card = new Card("1000200030004001", "0000");

        when(cardService.checkCard(card)).thenReturn(true);
        when(cardService.checkBalance(card)).thenReturn(Optional.of(balance));

        atm.acceptCard(card);
        assertEquals(balance.getCurrency(), atm.getBalance().map(Balance::getCurrency).orElse(null));
        assertEquals(balance.getValue(), atm.getBalance().map(Balance::getValue).orElse(null));
    }

    @Test
    @DisplayName("Card - balance without card")
    void getCardBalanceWithoutCard() {
        assertNull(atm.getBalance().map(Balance::getCurrency).orElse(null));
        assertNull(atm.getBalance().map(Balance::getValue).orElse(null));
    }
}
