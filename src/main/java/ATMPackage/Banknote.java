package ATMPackage;

import Uniform.Currency;
import lombok.Getter;


@Getter
public enum Banknote {

    RUR_100 (100,Currency.RUR),
    RUR_500 (500,Currency.RUR),
    RUR_1000 (1000,Currency.RUR),
    RUR_5000 (5000,Currency.RUR),
    USD_20 (20,Currency.USD),
    USD_50 (50,Currency.USD),
    USD_100 (100,Currency.USD),
    EUR_20 (20,Currency.EUR),
    EUR_50 (50,Currency.EUR),
    EUR_100 (100,Currency.EUR);

    private final int denomination;
    private final Currency currency;

    Banknote(int denomination, Currency currency) {
        this.denomination = denomination;
        this.currency = currency;
    }
}
