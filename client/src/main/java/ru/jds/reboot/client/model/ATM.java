package ru.jds.reboot.client.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ATM extends AbstractATM {

    @Value("${processing.url.card}")
    private String processingUrlCard;

    @Value("${processing.url.balance}")
    private String processingUrlBalance;

    private final RestTemplate restTemplate;

    @Override
    protected boolean checkCard(Card card) {
        log.info("check card: {}", card.getNumber());
        ResponseEntity<Object> response = restTemplate.postForEntity(processingUrlCard, card, Object.class);
        return response.getStatusCode() == HttpStatus.OK;
    }

    @Override
    protected Optional<Balance> checkBalance(Card card) {
        Balance balance = restTemplate.postForObject(processingUrlBalance, card, Balance.class);
        return Optional.ofNullable(balance);
    }
}
