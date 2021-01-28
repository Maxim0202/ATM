package Processing.Transaction;

import Processing.Exception.ServerErrorCodes;
import Processing.Exception.ServerException;
import Uniform.Money;
import Uniform.Plastic.DebitCard;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NonNull
public class Server {
    private List<Cash> cashes = new ArrayList<>();

    boolean containsTransaction(Cash cash) {
        return cashes.stream().anyMatch(x -> x.doubleCheck(cash));
    }

    public void executeTransaction(DebitCard card, Money money, int deviceId, int operationId) throws ServerException {

        Cash cash = new Cash(card, money, deviceId, operationId);

        if (containsTransaction(cash)) {
            cash.setStatus(Status.REJECTED);
            throw new ServerException(ServerErrorCodes.ERR_DOUBLE_TRANSACTION, cash.getCard().getNumber(), String.valueOf(cash.getDeviceId()));
        }

        try {
            cash.execute(x -> x.signum() > 0);
            cash.setStatus(Status.OK);
            cashes.add(cash);
        }
        catch (Exception e){
            cash.setStatus(Status.FAILED);
        }
    }
}
