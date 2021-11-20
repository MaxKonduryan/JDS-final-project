package ru.jds.reboot.server.model;

import org.springframework.stereotype.Component;
import ru.jds.reboot.server.service.CardService;

@Component
public class ProcessingServer extends Processing {
    public ProcessingServer(CardService cardService) {
        super(cardService);
    }
}
