package ru.jds.reboot.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.jds.reboot.client.model.AtmClient;
import ru.jds.reboot.client.model.Balance;
import ru.jds.reboot.client.model.Card;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class ClientController {

    @Value("${message.title}")
    private String messageTitle;

    @Value("${message.error.na}")
    private String messageErrorNA;

    @Value("${message.error.invalid}")
    private String messageErrorInvalid;

    @Value("${message.welcome}")
    private String messageWelcome;

    @Autowired
    private KafkaTemplate<Integer, Card> kafkaTemplate;

    private final AtomicReference<AtmClient> atm;

    public ClientController(AtmClient atm) {
        this.atm = new AtomicReference<>(atm);
    }

    @GetMapping(value = {"/", "/index"})
    public String getIndex() {
        if (atm.get().isPresentCard()) {
            return "redirect:/card";
        } else {
            return "redirect:/welcome";
        }
    }

    @GetMapping("/welcome")
    public String getWelcome(Model model) {
        if (atm.get().isPresentCard()) {
            return "redirect:/card";
        } else {
            model.addAttribute("title", messageTitle);
            return "welcome";
        }
    }

    @PostMapping("/card")
    public String postCard(
            @RequestParam("number") String number,
            @RequestParam("pin") String pinCode
    ) {
        if (number.length() > 0 && pinCode.length() > 0) {
            boolean isAccepted;
            try {
                isAccepted = atm.get().acceptCard(new Card(number, pinCode));
            } catch (Exception e) {
                return "redirect:/na";
            }
            return isAccepted ? "redirect:/card" : "redirect:/invalid";
        } else
            return "redirect:/invalid";
    }

    @GetMapping("/block")
    public String blockCard() {
        if (atm.get().isPresentCard()) {
            kafkaTemplate.send("block", Integer.valueOf(atm.get().getCard().get().hashCode()), atm.get().getCard().get());
        }
        return "redirect:/card";
    }

    @GetMapping("/card")
    public String getCard(Model model) {
        if ( ! atm.get().isPresentCard()) {
            return "redirect:/welcome";
        } else {
            model.addAttribute("title", messageTitle);
            model.addAttribute("message", "Операции по карте");
            return "card";
        }
    }

    @GetMapping("/balance")
    public String getBalance(Model model) {
        if ( ! atm.get().isPresentCard()) {
            return "redirect:/welcome";
        } else {
            Optional<Balance> balance;
            try {
                balance = atm.get().getBalance();
            } catch (Exception e) {
                return "redirect:/na";
            }
            if (balance.isPresent()) {
                model.addAttribute("title", messageTitle);
                model.addAttribute("message", "Текущий баланс");
                model.addAttribute("currency", balance.get().getCurrency());
                model.addAttribute("value", balance.get().getValue());
                return "balance";
            } else {
                return "redirect:/invalid";
            }
        }
    }

    @GetMapping("/return")
    public String getReturn(Model model) {
        if (atm.get().isPresentCard()) {
            atm.get().clearCard();
        }
        return "redirect:/welcome";
    }

    @GetMapping("/invalid")
    public String getInvalid(Model model) {
        model.addAttribute("title", messageTitle);
        model.addAttribute("message", messageErrorInvalid);
        return "invalid";
    }

    @GetMapping("/na")
    public String getNA(Model model) {
        model.addAttribute("title", messageTitle);
        model.addAttribute("message", messageErrorNA);
        return "na";
    }

}
