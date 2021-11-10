package ru.jds.reboot.client.model;

import org.springframework.stereotype.Component;
import ru.jds.reboot.client.service.CardService;

@Component
public class AtmClient extends Atm {
    public AtmClient(CardService cardService) {
        super(cardService);
    }
}
