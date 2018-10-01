package cz.muni.fi.pa165.currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;


/**
 * This is base implementation of {@link CurrencyConvertor}.
 *
 * @author petr.adamek@embedit.cz
 */
public class CurrencyConvertorImpl implements CurrencyConvertor {

    private final ExchangeRateTable exchangeRateTable;
    private final Logger logger = LoggerFactory.getLogger(CurrencyConvertorImpl.class);

    public CurrencyConvertorImpl(ExchangeRateTable exchangeRateTable) {
        this.exchangeRateTable = exchangeRateTable;
    }

    @Override
    public BigDecimal convert(Currency sourceCurrency, Currency targetCurrency, BigDecimal sourceAmount) {
        logger.trace("Converting source: {}, to target: {}, amount: {}", sourceCurrency, targetCurrency, sourceAmount);
        if (sourceCurrency == null) {
            throw new IllegalArgumentException("Source currency cannot be null");
        }

        if (targetCurrency == null) {
            throw new IllegalArgumentException("Target currency cannot be null");
        }

        if (sourceAmount == null) {
            throw new IllegalArgumentException("Source amount cannot be null");
        }

        try {
            BigDecimal rate = this.exchangeRateTable.getExchangeRate(sourceCurrency, targetCurrency);
            if (rate == null) {
                logger.warn("Unknown exchange rate, source:{}, target: {}, amount: {}", sourceCurrency, targetCurrency, sourceAmount);
                throw new UnknownExchangeRateException("Unknown exchange rate");
            }
            return rate.multiply(sourceAmount).setScale(2, RoundingMode.DOWN);
        } catch (ExternalServiceFailureException e) {
            logger.error(
                    "External failure exception, source: {}, target: {}, amount: {}, exception: {}",
                    sourceCurrency,
                    targetCurrency,
                    sourceAmount,
                    e
            );
            throw new UnknownExchangeRateException(e.getMessage());
        }
    }

}
