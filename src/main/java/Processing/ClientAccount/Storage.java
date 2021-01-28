package Processing.ClientAccount;

import Processing.Exception.ServerErrorCodes;
import Processing.Exception.ServerException;
import Uniform.Plastic.DebitCard;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NonNull
public class Storage {
    private List<Account<DebitCard>> accounts = new ArrayList<>();

    public Account<DebitCard> getAccountByCard(String cardNumber) throws ServerException {
        return accounts.stream()
                .filter(s -> s.getCard().getNumber().equals(cardNumber))
                .findAny()
                .orElseThrow(() -> new ServerException(ServerErrorCodes.ERR_NOT_FOUND_ACCOUNT, "*" + cardNumber.substring(12, 15)));
    }
}
