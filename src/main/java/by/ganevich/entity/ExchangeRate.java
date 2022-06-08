package by.ganevich.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "exchangeRates")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency")
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @Column(name = "rate_in")
    private Double rateIn;

    @Column(name = "rate_out")
    private Double rateOut;
}
