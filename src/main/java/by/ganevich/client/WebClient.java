package by.ganevich.client;

import by.ganevich.entity.ExchangeRate;
import by.ganevich.parser.JsonParser;
import by.ganevich.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WebClient {

    private final ExchangeRateService exchangeRateService;
    private final JsonParser jsonParser;
    @Value("${api.url}")
    private String url;

    public List<ExchangeRate> getCurrentRates() {
        RestTemplate restTemplate = new RestTemplate();
        Object[] rates = restTemplate.getForObject(url, Object[].class);
        List<ExchangeRate> exchangeRates = jsonParser.parseJson(rates[0].toString(), exchangeRateService.getAll());
        return exchangeRates;
    }
}
