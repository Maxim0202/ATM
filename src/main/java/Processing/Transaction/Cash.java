package Processing.Transaction;

import Processing.ClientAccount.Account;
import Processing.Exception.ServerErrorCodes;
import Processing.Exception.ServerException;
import Uniform.Money;
import Uniform.Plastic.DebitCard;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Predicate;

@Getter
public class Cash {

    private final Money money;
    @Setter
    private Status status;
    private final DebitCard card;
    private final LocalDateTime datetime;
    private final int deviceId;
    private final int operationId;
    @Setter
    private BigDecimal rate;
    @Setter
    private Account<DebitCard> account;

    public Cash(DebitCard card, Money money, int deviceId, int operationId) {
        this.money = money;
        this.card = card;
        this.deviceId = deviceId;
        this.operationId = operationId;
        datetime = LocalDateTime.now();
    }

    public void execute(Predicate<BigDecimal> predicate) throws ServerException {
        account.decrease(money, rate);
        if (!predicate.test(account.getBalance().getValue()))
            throw new ServerException(ServerErrorCodes.ERR_NOT_ENOUGH_MONEY);
    }

    public boolean doubleCheck(Cash cash) {
        return deviceId == cash.getDeviceId() &&
                operationId == cash.getOperationId() &&
                Objects.equals(money, cash.getMoney()) &&
                Objects.equals(card, cash.getCard());
    }
}
