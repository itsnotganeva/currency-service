package by.ganevich.service;

import by.ganevich.entity.Currency;
import by.ganevich.entity.ExchangeRate;
import by.ganevich.repository.ExchangeRateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRate save(ExchangeRate exchangeRate) {
        return exchangeRateRepository.save(exchangeRate);
    }

    public List<ExchangeRate> getAll() {
        return exchangeRateRepository.findAll();
    }

    public ExchangeRate getByCurrency(Currency currency) {
        return exchangeRateRepository.findByCurrency(currency);
    }
}
