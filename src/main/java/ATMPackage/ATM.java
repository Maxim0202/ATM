package ATMPackage;

import ATMPackage.Exception.ATMErrorCodes;
import ATMPackage.Exception.ATMException;
import Processing.Transaction.Server;
import Uniform.Connect.Connect;
import Uniform.Currency;
import Uniform.Money;
import Uniform.Plastic.DebitCard;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@NonNull
@Getter
public class ATM {

    private final Connect connect = new Connect("Bank", 777);
    private List<Cassets> cassettes = new ArrayList<>();
    private final int deviceId;
    private int operationId;

    public ATM(int deviceId, int operationId) {
        this.deviceId = deviceId;
        this.operationId = operationId;
    }

    public void setCassets(List<Cassets> cassets) {
        this.cassettes = cassets;
        cassettes.sort((Cassets o1, Cassets o2) -> o2.getBanknote().getDenomination() - o1.getBanknote().getDenomination());
    }

    public int sumByCurrency(Currency currency) {
        return cassettes.stream()
                .filter(c -> c.getBanknote().getCurrency().equals(currency))
                .mapToInt(c -> c.getBanknote().getDenomination() * c.getCurrentAmount())
                .sum();
    }

    public boolean checkAvailableCash(Currency currency, int value) {
        return sumByCurrency(currency) >= value;
    }

    public Map<Banknote, Integer> getCash(DebitCard card, Currency currency, int value) throws ATMException {

        cleanCache();
        operationId++;

        if (!checkAvailableCash(currency, value))
            throw new ATMException(ATMErrorCodes.ERR_ATM_CASH_AVAIL, String.valueOf(value), currency.getCode());

        Server server = connect.open();
        try {
            server.executeTransaction(card, new Money(BigDecimal.valueOf(value), currency), deviceId, operationId);
        } catch (Exception e) {
            throw new ATMException(ATMErrorCodes.ERR_SERVER, e.getMessage());
        }
        connect.close();
        return getBanknotes(currency, value);
    }

    public void cleanCache() {
        cassettes.forEach(x -> x.setCache(0));
    }

    public Map<Banknote, Integer> getBanknotes(Currency currency, int value) throws ATMException {
        int tmpVal = value;
        Map<Banknote, Integer> banknotes = new HashMap<>();
        for (Cassets cassets : cassettes) {
            if (cassets.getBanknote().getCurrency().equals(currency) && cassets.getCurrentAmount() > 0) {
                int count = Math.min(cassets.getCurrentAmount(), value / cassets.getBanknote().getDenomination());
                banknotes.put(cassets.getBanknote(), banknotes.get(cassets.getBanknote()) != null ? banknotes.get(cassets.getBanknote()) : +count);
                cassets.setCache(count);
                tmpVal = tmpVal - count * cassets.getBanknote().getDenomination();
            }
        }
        if (value == 0)
            cassettes.forEach(Cassets::popBanknotes);
        else
            throw new ATMException(ATMErrorCodes.ERR_ATM_CASH_AVAIL, String.valueOf(value), currency.getCode());
        return banknotes;
    }

}
