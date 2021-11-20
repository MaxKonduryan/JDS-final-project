package ru.jds.reboot.client.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.jds.reboot.client.model.Balance;
import ru.jds.reboot.client.model.Card;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardRestService implements CardService{

    @Value("${processing.url.card}")
    private String processingUrlCard;

    @Value("${processing.url.balance}")
    private String processingUrlBalance;

    private final RestTemplate restTemplate;

    @Override
    public boolean checkCard(Card card) {
        log.info("check card: {}", card.getNumber());
        ResponseEntity<Object> response = restTemplate.postForEntity(processingUrlCard, card, Object.class);
        return response.getStatusCode() == HttpStatus.OK;
    }

    @Override
    public Optional<Balance> checkBalance(Card card) {
        Balance balance = restTemplate.postForObject(processingUrlBalance, card, Balance.class);
        return Optional.ofNullable(balance);
    }

}
