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
import ru.jds.reboot.server.model.ProcessingCenter;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ServerController {

    private AtomicReference<ProcessingCenter> proc;

    public ServerController(ProcessingCenter proc) {
        this.proc = new AtomicReference<>(proc);
    }

    @PostMapping("/card")
    public ResponseEntity<Object> checkCard(@RequestBody Card card) {
        return ResponseEntity.status(proc.get().checkCard(card) ? HttpStatus.OK : HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/balance")
    public Balance checkBalance(@RequestBody Card card) {
        return proc.get().checkBalance(card).orElseThrow(CardNotFoundException::new);
    }

}
