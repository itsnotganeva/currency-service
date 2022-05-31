package by.ganevich.controller;

import by.ganevich.service.dto.ExchangeRateDto;
import by.ganevich.entity.Currency;
import by.ganevich.entity.ExchangeRate;
import by.ganevich.mapper.ExchangeRateMapper;
import by.ganevich.parser.JsonParser;
import by.ganevich.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@AllArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;
    private final ExchangeRateMapper exchangeRateMapper;
    private final JsonParser jsonParser;

    @Scheduled(fixedRate = 3600000)
    @GetMapping(value = "/rates-from-api")
    public ResponseEntity<List<ExchangeRateDto>> getRate() {

        String url = "https://belarusbank.by/api/kursExchange";
        RestTemplate restTemplate = new RestTemplate();

        Object[] rates = restTemplate.getForObject(url, Object[].class);

        List<ExchangeRate> exchangeRates = jsonParser.parseJson(rates[0].toString(), exchangeRateService.readAll());
        exchangeRates.forEach(rate -> exchangeRateService.save(rate));

        List<ExchangeRateDto> exchangeRatesDto = exchangeRateMapper.toDtoList(exchangeRates);
        return new ResponseEntity<>(exchangeRatesDto, HttpStatus.OK);
    }

    @GetMapping(value = "/rates")
    public ResponseEntity<List<ExchangeRateDto>> readRates() {
        List<ExchangeRate> exchangeRates = exchangeRateService.readAll();
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
