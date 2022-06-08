package by.ganevich.dto;

import by.ganevich.entity.Currency;
import lombok.Data;

@Data
public class ExchangeRateDto {

    private Long id;

    private Currency currency;

    private Double rateIn;

    private Double rateOut;
}
