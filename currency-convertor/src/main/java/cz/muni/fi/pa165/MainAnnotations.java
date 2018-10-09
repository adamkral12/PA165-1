package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import cz.muni.fi.pa165.currency.ExchangeRateTableImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

public class MainAnnotations {
    private static Currency CZK = Currency.getInstance("CZK");
    private static Currency EUR = Currency.getInstance("EUR");

    public static void main(String [] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ExchangeRateTableImpl.class);
        applicationContext.register(CurrencyConvertorImpl.class);

        applicationContext.refresh();

        CurrencyConvertor currencyConvertor = (CurrencyConvertorImpl) applicationContext.getBean((CurrencyConvertorImpl.class));

        System.out.print(currencyConvertor.convert(EUR, CZK, new BigDecimal(1)));

    }

}
