package ATM;

import ATMPackage.ATM;
import ATMPackage.Banknote;
import ATMPackage.Cassets;
import ATMPackage.Exception.ATMException;
import Uniform.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ATMtest {

    ATM atm = new ATM(1, 1);
    List<Cassets> cassettes = new ArrayList<>();

    @BeforeEach
    void init() {
        cassettes.add(new Cassets(1, Banknote.RUR_100, 300));
        cassettes.add(new Cassets(2, Banknote.RUR_500, 250));
        cassettes.add(new Cassets(3, Banknote.RUR_1000, 100));
        cassettes.add(new Cassets(4, Banknote.RUR_5000, 50));
        cassettes.add(new Cassets(5, Banknote.USD_20, 200));
        cassettes.add(new Cassets(6, Banknote.USD_50, 50));
        cassettes.add(new Cassets(7, Banknote.USD_100, 110));
        atm.setCassets(cassettes);
    }

    @Test
    void testSumByCurrency() {
        Assertions.assertEquals(1250000, atm.sumByCurrency(Currency.RUR));
    }

    @Test
    void testCheckAvailableCash() {
        assertTrue(atm.checkAvailableCash(Currency.RUR, 1250000));
        Assertions.assertFalse(atm.checkAvailableCash(Currency.RUR, 1250001));
    }

    @Test
    void testGetBanknotesDefault() throws ATMException {
        Map<Banknote, Integer> cash = atm.getBanknotes(Currency.USD, 15650);
        Assertions.assertEquals(cash.get(Banknote.USD_100), 102);
        Assertions.assertEquals(cash.get(Banknote.USD_50), 123);
        Assertions.assertEquals(cash.get(Banknote.USD_20), 144);
    }

    @Test
    void testGetBanknotesException() {
        ATMException thrown = Assertions.assertThrows(
                ATMException.class,
                () -> atm.getBanknotes(Currency.USD, 25440));
        assertTrue(thrown.getMessage().contains("Невозможно выдать введенную сумму 28220 USD. Код ошибки: 1"));
    }
}
