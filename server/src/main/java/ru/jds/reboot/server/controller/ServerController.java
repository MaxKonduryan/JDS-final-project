package ru.jds.reboot.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jds.reboot.server.exception.CardNotFoundException;
import ru.jds.reboot.server.model.Balance;
import ru.jds.reboot.server.model.Card;
import ru.jds.reboot.server.model.ProcessingServer;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ServerController {

    private AtomicReference<ProcessingServer> processing;

    public ServerController(ProcessingServer processing) {
        this.processing = new AtomicReference<>(processing);
    }

    @PostMapping("/card")
    public ResponseEntity<Object> checkCard(@RequestBody Card card) {
        return ResponseEntity.status(processing.get().checkCard(card) ? HttpStatus.OK : HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/balance")
    public Balance checkBalance(@RequestBody Card card) {
        return processing.get().checkBalance(card).orElseThrow(CardNotFoundException::new);
    }

}
