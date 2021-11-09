package ru.jds.reboot.server.model;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.jds.reboot.server.service.CardService;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProcessingCenter extends AbstractProcessingCenter{

    @Autowired
    private final CardService cardService;

    @Override
    protected boolean isValidCard(Card card) {
        return cardService.isValidCard(card);
    }

    @Override
    protected Optional<Account> getAccount(Card card) {
        return cardService.getAccountByCard(card);
    }
}
