package ru.jds.reboot.server.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.jds.reboot.server.service.CardService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessingTest {

    CardService cardService;
    Processing processingCenter;

    @BeforeEach
    void beforeEach() {
        cardService = mock(CardService.class);
        processingCenter = new Processing(cardService);
    }

    @Test
    @DisplayName("Card - check card")
    void checkCard() {
        Card card = new Card("1000200030004000", "0000");

        when(cardService.isValidCard(card)).thenReturn(true);

        assertTrue (processingCenter.checkCard(card));
        assertFalse(processingCenter.checkCard(new Card("1000200030004000", "1111")));
        assertFalse(processingCenter.checkCard(new Card("1000200030009999", "0000")));
    }

    @Test
    @DisplayName("Card - check balance")
    void checkBalance() {
        Card card = new Card("1000200030004000", "0000");
        Set<Card> cards = new HashSet<>(1);
        cards.add(card);
        Account account = new Account(
                cards,
                new Balance(Currency.RUR, BigDecimal.TEN)
        );

        when(cardService.getAccount(card)).thenReturn(Optional.of(account));

        assertEquals(
                account.getBalance().getCurrency(),
                processingCenter.checkBalance(card).map(Balance::getCurrency).orElse(null)
        );
        assertEquals(
                account.getBalance().getValue(),
                processingCenter.checkBalance(card).map(Balance::getValue).orElse(null)
        );
    }
}
