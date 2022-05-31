package by.ganevich.parser;

import by.ganevich.entity.Currency;
import by.ganevich.entity.ExchangeRate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JsonParser {

    public List<ExchangeRate> parseJson(String str, List<ExchangeRate> rates) {
        Map<String, String> parsedJson = new HashMap<>();

        for (String value : str.substring(str.indexOf("{") + 1, str.indexOf("}") - 1).split(", ")) {
            String[] pairs = value.split("=", 2);
            parsedJson.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
        }
        if (rates.isEmpty()) {
            List<ExchangeRate> exchangeRates = new ArrayList<>();

            ExchangeRate usd = setExchangeRate(new ExchangeRate(), Currency.USD, parsedJson);
            ExchangeRate eur = setExchangeRate(new ExchangeRate(), Currency.EUR, parsedJson);
            ExchangeRate byn = setExchangeRate(new ExchangeRate(), Currency.BYN, parsedJson);

            exchangeRates.add(usd);
            exchangeRates.add(eur);
            exchangeRates.add(byn);
            return exchangeRates;
        } else {
            rates.stream().map(r -> setExchangeRate(r, r.getCurrency(), parsedJson));
            return rates;
        }
    }


    public ExchangeRate setExchangeRate(ExchangeRate rate, Currency currency, Map<String, String> parsedJson) {
        if (currency.equals(Currency.BYN)) {
            rate.setCurrency(currency);
            rate.setRateIn(Double.valueOf(1));
            rate.setRateOut(Double.valueOf(1));
        } else {
            rate.setCurrency(currency);
            rate.setRateIn(Double.valueOf(parsedJson.get(currency.name() + "_in")));
            rate.setRateOut(Double.valueOf(parsedJson.get(currency.name() + "_out")));
        }
        return rate;
    }
}
