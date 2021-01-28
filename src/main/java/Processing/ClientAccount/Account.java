package Processing.ClientAccount;


import Uniform.Money;
import Uniform.Plastic.Card;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;

@Getter
public class Account<T extends Card> {

    @NonNull
    private final int accountId;
    @NonNull
    private Money balance;
    private final T card;

    public Account(@NonNull int accountId, @NonNull Money balance, T card) {
        this.accountId = accountId;
        this.balance = balance;
        this.card = card;
    }

    public void decrease(Money value, BigDecimal rate) {
        balance = new Money(balance.getValue().subtract(value.getValue().multiply(rate)), balance.getCurrency());
    }

    public void increase(Money value, BigDecimal rate) {
        balance = new Money(balance.getValue().add(value.getValue().multiply(rate)), balance.getCurrency());
    }
}
