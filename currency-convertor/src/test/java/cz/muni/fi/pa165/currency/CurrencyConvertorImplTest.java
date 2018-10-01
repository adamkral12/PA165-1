package cz.muni.fi.pa165.currency;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrencyConvertorImplTest {

    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency EUR = Currency.getInstance("EUR");

    private final ExchangeRateTable exchangeRateTable = mock(ExchangeRateTable.class);
    private final CurrencyConvertor currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);

    @Test
    public void testConvert() throws ExternalServiceFailureException {
        // Don't forget to test border values and proper rounding.

        when(exchangeRateTable.getExchangeRate(USD, EUR)).thenReturn(new BigDecimal("12"));
        assertEquals(new BigDecimal("120.00"), currencyConvertor.convert(USD, EUR, new BigDecimal("10")));

        when(exchangeRateTable.getExchangeRate(USD, EUR)).thenReturn(new BigDecimal("0"));
        assertEquals(new BigDecimal("0.00"), currencyConvertor.convert(USD, EUR, new BigDecimal("32.323")));

        when(exchangeRateTable.getExchangeRate(USD, EUR)).thenReturn(new BigDecimal("-35.2"));
        assertEquals(new BigDecimal("-352000.00"), currencyConvertor.convert(USD, EUR, new BigDecimal("10000")));

        when(exchangeRateTable.getExchangeRate(USD, EUR)).thenReturn(new BigDecimal("0.5"));
        assertEquals(new BigDecimal("1.25"), currencyConvertor.convert(USD, EUR, new BigDecimal("2.5")));

    }

    @Test
    public void testConvertWithNullSourceCurrency() {
        assertThatThrownBy(() -> {
            currencyConvertor.convert(null, EUR, new BigDecimal("32.323"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Source currency cannot be null");
    }

    @Test
    public void testConvertWithNullTargetCurrency() {
        assertThatThrownBy(() -> {
            currencyConvertor.convert(EUR, null, new BigDecimal("32.323"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Target currency cannot be null");
    }

    @Test
    public void testConvertWithNullSourceAmount() {
        assertThatThrownBy(() -> {
            currencyConvertor.convert(EUR, USD, null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Source amount cannot be null");
    }

    @Test
    public void testConvertWithUnknownCurrency() throws ExternalServiceFailureException {
        when(exchangeRateTable.getExchangeRate(USD, EUR)).thenReturn(null);
        assertThatExceptionOfType(UnknownExchangeRateException.class)
                .isThrownBy(() -> currencyConvertor.convert(USD, EUR, BigDecimal.ONE));
    }

    @Test
    public void testConvertWithExternalServiceFailure() throws ExternalServiceFailureException {
        when(exchangeRateTable.getExchangeRate(USD, EUR)).thenThrow(ExternalServiceFailureException.class);
        assertThatExceptionOfType(UnknownExchangeRateException.class)
                .isThrownBy(() -> currencyConvertor.convert(USD, EUR, BigDecimal.ONE));
    }

}
