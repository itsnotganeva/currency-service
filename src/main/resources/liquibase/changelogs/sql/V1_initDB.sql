create table exchangeRates
(
    id bigSerial PRIMARY KEY,
    currency varchar(10) not null UNIQUE,
    rate_in real not null,
    rate_out real not null
);