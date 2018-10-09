package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

public class MainXml {

    private static Currency CZK = Currency.getInstance("CZK");
    private static Currency EUR = Currency.getInstance("EUR");

    public static void main(String [] args) {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CurrencyConvertorImpl currencyConvertor = ((BeanFactory) appContext).getBean(CurrencyConvertorImpl.class);
        System.out.print(currencyConvertor.convert(EUR, CZK, new BigDecimal(1)));

    }
}
