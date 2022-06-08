package by.ganevich.scheduling;

import by.ganevich.controller.ExchangeRateController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class Scheduler {

    private final ExchangeRateController exchangeRateController;

    @Scheduled(fixedRate = 3600000)
    private void invokeGetRates() {
        exchangeRateController.getRate();
        log.info("method is invoked");
    }
}
