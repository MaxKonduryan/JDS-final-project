package ru.jds.reboot.server.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;
import ru.jds.reboot.server.entity.CardEntity;
import ru.jds.reboot.server.model.Account;
import ru.jds.reboot.server.model.Balance;
import ru.jds.reboot.server.model.Card;
import ru.jds.reboot.server.repository.CardCrudRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardCrudService implements CardService {
    private CardCrudRepository cardCrudRepository;

    private Optional<CardEntity> checkCard(Card card) {
        return cardCrudRepository
                .findById(card.getNumber())
                .filter(cardEntity -> PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(card.getPinCode(), cardEntity.getPinCode()))
                .filter(cardEntity -> ! cardEntity.isBlocked())
                ;
    }

    public void blockCard(Card card) {
        checkCard(card).ifPresent(cardEntity -> {
            cardEntity.setBlocked(true);
            cardCrudRepository.save(cardEntity);
        });
    }

    @Override
    public boolean isValidCard(Card card) {
        return checkCard(card).isPresent();
    }

    @Override
    public Optional<Account> getAccount(Card card) {
        return checkCard(card)
                .map(CardEntity::getAccount)
                .map(accountEntity ->
                        new Account(
                                accountEntity.getCards().stream()
                                        .map(cardEntity ->
                                                new Card(
                                                        cardEntity.getNumber(),
                                                        cardEntity.getPinCode()
                                                )
                                        )
                                        .collect(Collectors.toSet()),
                                new Balance(
                                        accountEntity.getCurrency(),
                                        accountEntity.getBalance()
                                )
                        )
                )
        ;
    }
}
