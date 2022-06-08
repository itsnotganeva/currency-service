package by.ganevich.controller;

import by.ganevich.client.WebClient;
import by.ganevich.dto.ExchangeRateDto;
import by.ganevich.entity.Currency;
import by.ganevich.entity.ExchangeRate;
import by.ganevich.mapper.ExchangeRateMapper;
import by.ganevich.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;
    private final ExchangeRateMapper exchangeRateMapper;
    private final WebClient webClient;


    @GetMapping(value = "/rates-from-api")
    public ResponseEntity<List<ExchangeRateDto>> getRate() {
        List<ExchangeRate> exchangeRates = webClient.getCurrentRates();
        exchangeRates.forEach(rate -> exchangeRateService.save(rate));
        log.info("Exchange rates are updated");
        return new ResponseEntity<>(exchangeRateMapper.toDtoList(exchangeRates), HttpStatus.OK);
    }

    @GetMapping(value = "/rates")
    public ResponseEntity<List<ExchangeRateDto>> readRates() {
        List<ExchangeRate> exchangeRates = exchangeRateService.getAll();
        List<ExchangeRateDto> exchangeRatesDto = exchangeRateMapper.toDtoList(exchangeRates);
        return new ResponseEntity<>(exchangeRatesDto, HttpStatus.OK);
    }

    @GetMapping(value = "/rates/{currency}")
    public ResponseEntity<ExchangeRateDto> findRate(@PathVariable(name = "currency") Currency currency) {
        ExchangeRate exchangeRate = exchangeRateService.getByCurrency(currency);
        ExchangeRateDto exchangeRateDto = exchangeRateMapper.toDto(exchangeRate);
        return new ResponseEntity<>(exchangeRateDto, HttpStatus.OK);
    }

}
