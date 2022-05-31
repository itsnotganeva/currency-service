package by.ganevich.mapper;

import by.ganevich.service.dto.ExchangeRateDto;
import by.ganevich.entity.ExchangeRate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {
    ExchangeRateDto toDto(ExchangeRate exchangeRate);

    ExchangeRate toEntity(ExchangeRateDto exchangeRateDto);

    List<ExchangeRate> toEntityList(List<ExchangeRateDto> exchangeRatesDto);

    List<ExchangeRateDto> toDtoList(List<ExchangeRate> exchangeRates);
}
