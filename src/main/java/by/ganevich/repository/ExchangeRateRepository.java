package by.ganevich.repository;

import by.ganevich.entity.Currency;
import by.ganevich.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findByCurrency(Currency currency);
}
