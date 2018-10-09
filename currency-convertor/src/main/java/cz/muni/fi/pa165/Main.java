package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

public class Main {
    private static Currency CZK = Currency.getInstance("CZK");
    private static Currency EUR = Currency.getInstance("EUR");

    public static void main(String ... args) {
        springJavaConfigContext();
    }

    private static void springJavaConfigContext() {

        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(MainJavaConfig.class);

        CurrencyConvertor currencyConvertor
                = applicationContext.getBean("currencyConvertor", CurrencyConvertor.class);

        System.err.println(currencyConvertor.convert(
                EUR, CZK, new BigDecimal("1")
        ));
    }
}
