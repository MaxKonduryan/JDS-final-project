package ru.jds.reboot.server.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AbstractProcessingCenterTest {

    AbstractProcessingCenter processingCenter;

    @BeforeEach
    void beforeEach() {
        processingCenter = mock(
                AbstractProcessingCenter.class,
                withSettings()
                        .useConstructor()
                        .defaultAnswer(CALLS_REAL_METHODS)
        );
    }

    @Test
    @DisplayName("Card - check card")
    void checkCard() {
        when(processingCenter.existAccount(new Card("1000200030004000", "0000"))).thenReturn(true);

        assertTrue (processingCenter.checkCard(new Card("1000200030004000", "0000")));
        assertFalse(processingCenter.checkCard(new Card("1000200030004000", "1111")));
        assertFalse(processingCenter.checkCard(new Card("1000200030009999", "0000")));
    }

    @Test
    @DisplayName("Card - check balance")
    void checkBalance() {
        Account account = new Account(
                new Card("1000200030004000", "0000"),
                new Balance(Currency.RUR, BigDecimal.TEN)
        );

        when(processingCenter.getAccount(new Card("1000200030004000", "0000"))).thenReturn(account);

        assertEquals(
                account.getBalance().getCurrency(),
                processingCenter.checkBalance(new Card("1000200030004000", "0000")).getCurrency()
        );
        assertEquals(
                account.getBalance().getValue(),
                processingCenter.checkBalance(new Card("1000200030004000", "0000")).getValue()
        );
    }
}
