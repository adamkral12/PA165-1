package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import cz.muni.fi.pa165.currency.ExchangeRateTable;
import cz.muni.fi.pa165.currency.ExchangeRateTableImpl;
import org.springframework.context.annotation.*;

@Configuration
//@ComponentScan("cz.muni.fi.pa165.currency")
@EnableAspectJAutoProxy
public class MainJavaConfig {

    @Bean
    public ExchangeRateTableImpl exchangeRateTable() {
        return new ExchangeRateTableImpl();
    }

    @Bean
    public CurrencyConvertor currencyConvertor(ExchangeRateTable exchangeRateTable) {
        return new CurrencyConvertorImpl(exchangeRateTable);
    }

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

}
